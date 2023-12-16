package com.example.newsappcompose.domain.usecase.remote_usecase

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(source: List<String>, query: String): Flow<PagingData<Article>> {
        return newsRepository.searchNews(source, query)
    }
}