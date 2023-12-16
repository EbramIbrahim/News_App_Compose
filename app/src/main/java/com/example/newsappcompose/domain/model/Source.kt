package com.example.newsappcompose.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable