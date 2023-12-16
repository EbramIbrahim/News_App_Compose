package com.example.newsappcompose.data.remote

import com.example.newsappcompose.data.dto.NewsResponse
import com.example.newsappcompose.utils.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("page") page: Int,
        @Query("sources") source: String,
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


}






