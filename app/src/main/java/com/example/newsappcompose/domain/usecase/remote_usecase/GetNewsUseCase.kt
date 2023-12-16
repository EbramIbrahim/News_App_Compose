package com.example.newsappcompose.domain.usecase.remote_usecase

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase(
    private val repository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return repository.getNews(sources)
    }

}