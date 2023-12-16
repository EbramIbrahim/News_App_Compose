package com.example.newsappcompose.domain.usecase.local_usecase

import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllArticleUseCase @Inject constructor(
    private val repository: NewsRepository

) {


     operator fun invoke():Flow<List<Article>> {
        return repository.getAllArticles()
    }

}