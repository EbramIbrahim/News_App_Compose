package com.example.newsappcompose.presentation.favorite_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsappcompose.presentation.navigation.Screens
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.presentation.state_event.NewsState


@Composable
fun FavoriteScreen(
    onEvent: (NewsEvent) -> Unit,
    state: NewsState,
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        onEvent(NewsEvent.GetAllArticles)
    }


    LazyColumn(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        items(state.articleEntities.size) {
            FavoriteCard(
                article = state.articleEntities[it],
                onArticleClick = { article ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "article",
                        value = article
                    )
                    navController.navigate(Screens.DetailsScreen.route)
                }
            )
        }
    }


}







