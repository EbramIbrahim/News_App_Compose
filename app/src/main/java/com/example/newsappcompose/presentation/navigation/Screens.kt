package com.example.newsappcompose.presentation.navigation

sealed class Screens(val route: String) {
    object OnBoardingScreen: Screens("onboarding_screen")
    object HomeScreen: Screens("home_screen")
    object SearchScreen: Screens("search_screen")
    object DetailsScreen: Screens("details_screen")
    object BookMarkScreen: Screens("book_mark_screen")

}
