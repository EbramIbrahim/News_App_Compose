package com.example.newsappcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.domain.model.Article

class NewsPaginationSource(
    private val newsApi: NewsApi,
    private val sources: String

):PagingSource<Int, Article>() {

    private var totalArticlesCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page = page, source = sources)
            totalArticlesCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if (totalArticlesCount == newsResponse.totalResults) null else page + 1
            )

        } catch (e: Exception){
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












