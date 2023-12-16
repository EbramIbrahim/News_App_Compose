package com.example.newsappcompose.utils

import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.OnBoarding

object Constant {

    val onBoardingList = listOf(
        OnBoarding(
            title = "Lorem Ipsum is simply dummy text",
            description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            image = R.drawable.onboarding1
        ),
        OnBoarding(
            title = "Lorem Ipsum is simply dummy text",
            description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            image = R.drawable.onboarding2
        ),
        OnBoarding(
            title = "Lorem Ipsum is simply dummy text",
            description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            image = R.drawable.onboarding3
        ),
    )

    const val USER_SETTING = "user_setting"
    const val USER_ENTRY = "user_entry"
    const val API_KEY = "f6f7ae69d4a045d5a3d11bfea8d272a7"
    const val BASE_URL = "https://newsapi.org/v2/"
}






