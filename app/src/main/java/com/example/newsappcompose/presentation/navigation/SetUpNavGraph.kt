package com.example.newsappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.presentation.details_screen.DetailsScreen
import com.example.newsappcompose.presentation.favorite_screen.FavoriteScreen
import com.example.newsappcompose.presentation.home_screen.NewsScreen
import com.example.newsappcompose.presentation.onboarding_screen.OnBoardingScreen
import com.example.newsappcompose.presentation.search_screen.SearchScreen
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.presentation.state_event.NewsState


@Composable
fun SetUpNavGraph(
    state: NewsState,
    onEvent: (NewsEvent) -> Unit,
    navHostController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {


    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable(route = Screens.OnBoardingScreen.route) {
            OnBoardingScreen(onEvent = onEvent, navController = navHostController)
        }


        composable(route = Screens.HomeScreen.route) {
            NewsScreen(newsState = state, navController = navHostController)
        }

        composable(route = Screens.SearchScreen.route) {
            SearchScreen(state = state, onEvent = onEvent, navigate = { navHostController.navigate(it) })
        }

        composable(
            route = Screens.DetailsScreen.route,
        ) {
            val article =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
            DetailsScreen(article = article, onEvent = onEvent) {

            }
        }

        composable(route = Screens.BookMarkScreen.route) {
            FavoriteScreen(onEvent = onEvent, state = state, navController = navHostController)
        }

    }



}











