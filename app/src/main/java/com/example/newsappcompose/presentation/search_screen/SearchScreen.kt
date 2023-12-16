package com.example.newsappcompose.presentation.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappcompose.presentation.common.ArticleList
import com.example.newsappcompose.presentation.common.SearchBar
import com.example.newsappcompose.presentation.navigation.Screens
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.presentation.state_event.NewsState


@Composable
fun SearchScreen(
    state: NewsState,
    onEvent: (NewsEvent) -> Unit,
    navigate: (String) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .statusBarsPadding()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                onEvent(NewsEvent.UpdateSearchQuery(it))
            },
            onSearch = { onEvent(NewsEvent.SearchNews) }
        )

        Spacer(modifier = Modifier.height(11.dp))
        state.searchArticles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = { navigate(Screens.DetailsScreen.route) })
        }


    }


}









