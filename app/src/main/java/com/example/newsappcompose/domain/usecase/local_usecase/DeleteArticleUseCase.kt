package com.example.newsappcompose.domain.usecase.local_usecase

import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: NewsRepository

) {


    suspend operator fun invoke(article: Article) {
        repository.deleteArticle(article)
    }

}