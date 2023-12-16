package com.example.newsappcompose.domain.model

import androidx.room.PrimaryKey


data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val urlToImage: String
)
