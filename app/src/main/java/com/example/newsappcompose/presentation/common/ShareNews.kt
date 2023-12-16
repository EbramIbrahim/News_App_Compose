package com.example.newsappcompose.presentation.common

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_VIEW
import android.net.Uri


fun Context.shareNews(url: String) {

    val intent = Intent(ACTION_SEND).apply {
        type = "plain/text"
        putExtra(Intent.EXTRA_TEXT, url)
    }

    val shareIntent = Intent.createChooser(
        intent, null
    )

    startActivity(shareIntent)
}

