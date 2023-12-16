package com.example.newsappcompose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.usecase.AllUseCase
import com.example.newsappcompose.presentation.navigation.Screens
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.presentation.state_event.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: AllUseCase
) : ViewModel() {


    private val _newsState: MutableStateFlow<NewsState> =
        MutableStateFlow(NewsState())
    val newsState = _newsState.asStateFlow()


    var startScreenState by mutableStateOf(Screens.OnBoardingScreen.route)
        private set


    var splashCondition by mutableStateOf(true)
        private set


    init {
        onEvent(NewsEvent.InitialScreens)
        getNews()
    }


    fun onEvent(event: NewsEvent) {
        when (event) {
            is NewsEvent.SaveUserEntry -> {
                saveUserAppEntry()
            }

            is NewsEvent.InitialScreens -> {
                readUserAppEntry()
            }

            is NewsEvent.SearchNews -> {
                getSearchNews()
            }

            is NewsEvent.UpdateSearchQuery -> {
                _newsState.update { it.copy(searchQuery = event.searchQuery) }
            }

            is NewsEvent.GetNews -> {
                getNews()
            }

            is NewsEvent.SaveNews -> {
                insertArticle(event.article)
            }
            is NewsEvent.DeleteNews -> {
                deleteArticle(event.article)
            }
            NewsEvent.GetAllArticles -> {
                getAllArticles()
            }
        }
    }


    private fun getSearchNews() {
        val searchArticles = useCase.searchNewsUseCase(
            source = listOf("bbc-news", "abc-news", "al-jazeera-english"),
            query = _newsState.value.searchQuery
        ).cachedIn(viewModelScope)
        _newsState.update { it.copy(searchArticles = searchArticles) }
    }


    private fun saveUserAppEntry() {
        viewModelScope.launch {
            useCase.saveUserEntryUseCase()
        }
    }

    private fun readUserAppEntry() {
        viewModelScope.launch {
            useCase.readUserEntryUseCase().onEach { appStarter ->
                startScreenState = if (appStarter) {
                    Screens.HomeScreen.route
                } else {
                    Screens.OnBoardingScreen.route
                }
                splashCondition = false

            }.launchIn(viewModelScope)
        }
    }

    private fun getNews() {
        val newsArticles = useCase.getNewsUseCase(
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)

        _newsState.update { it.copy(articles = newsArticles) }
    }


    private fun insertArticle(article: Article) = viewModelScope.launch {
        useCase.addArticleUseCase(article)
    }

    private fun deleteArticle(article: Article) = viewModelScope.launch {
        useCase.deleteArticleUseCase(article)
    }

    private fun getAllArticles() {
        useCase.getAllArticleUseCase().onEach { articles ->
            _newsState.update { it.copy(articleEntities = articles) }
        }.launchIn(viewModelScope)
    }




}

















