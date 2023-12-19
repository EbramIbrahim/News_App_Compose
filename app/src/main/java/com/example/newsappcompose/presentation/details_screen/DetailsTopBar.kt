package com.example.newsappcompose.presentation.details_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.newsappcompose.R
import com.example.newsappcompose.presentation.state_event.NewsState


@Composable
fun DetailsTopBar(
    onBackClick: () -> Unit,
    onBrowseClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    state: NewsState
) {

    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        contentColor = colorResource(id = R.color.body),
        backgroundColor = Color.Transparent,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onBrowseClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null
                )
            }
            IconButton(onClick = onBookMarkClick) {

                Icon(
                    painter = if (state.isBookMarked) painterResource(id = R.drawable.ic_filled_bookmark)
                    else painterResource(id = R.drawable.ic_border_bookmark),
                    contentDescription = null
                )
            }
        }
    )

}



















