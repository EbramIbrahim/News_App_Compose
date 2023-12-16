package com.example.newsappcompose.presentation.state_event

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NewsState(
    val searchArticles: Flow<PagingData<Article>>? = null,
    val articles: Flow<PagingData<Article>> = emptyFlow(),
    val articleEntities: List<Article> = emptyList(),
    val searchQuery: String = "",
)













