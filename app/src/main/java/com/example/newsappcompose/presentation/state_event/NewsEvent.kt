package com.example.newsappcompose.presentation.state_event

import com.example.newsappcompose.domain.model.Article

sealed interface NewsEvent {

    object SaveUserEntry: NewsEvent
    object InitialScreens: NewsEvent
    data class UpdateSearchQuery(val searchQuery: String): NewsEvent
    object SearchNews: NewsEvent
    object GetNews: NewsEvent
    class SaveNews(val article: Article): NewsEvent
    class DeleteNews(val article: Article): NewsEvent
    object GetAllArticles: NewsEvent

}