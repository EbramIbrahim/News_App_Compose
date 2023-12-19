package com.example.newsappcompose.domain.repository

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {


    suspend fun saveUserEntry()
    fun readUserEntry(): Flow<Boolean>
     fun getNews(sources: List<String>): Flow<PagingData<Article>>
     fun searchNews(sources: List<String>, query: String): Flow<PagingData<Article>>
    suspend fun addArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun getAllArticles(): Flow<List<Article>>
    suspend fun bookmark()
    suspend fun removeBookMark()
    fun readBookMark(): Flow<Boolean>

}









