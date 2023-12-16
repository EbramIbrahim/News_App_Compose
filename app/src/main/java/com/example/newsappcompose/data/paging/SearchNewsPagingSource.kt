package com.example.newsappcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val source: String,
    private val query: String
): PagingSource<Int, Article>() {


    private var totalArticlesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val searchResponse = newsApi.searchNews(page = page, source = source, query = query)
            totalArticlesCount += searchResponse.articles.size
            val articles = searchResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if (totalArticlesCount == searchResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}