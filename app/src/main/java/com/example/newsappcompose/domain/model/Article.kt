package com.example.newsappcompose.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news_table")
@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
   @Embedded val source: Source,
    val title: String,
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val urlToImage: String
): Parcelable