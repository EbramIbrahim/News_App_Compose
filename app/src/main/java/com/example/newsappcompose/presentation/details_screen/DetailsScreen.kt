package com.example.newsappcompose.presentation.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.presentation.common.shareNews
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.presentation.state_event.NewsState


@Composable
fun DetailsScreen(
    article: Article?,
    onEvent: (NewsEvent) -> Unit,
    onNavigateUp: () -> Unit,
    state: NewsState
) {

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(key1 = true) {
        onEvent(NewsEvent.ReadBookMark)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailsTopBar(
            onBackClick = onNavigateUp,

            onBrowseClick = {
                uriHandler.openUri(article?.url ?: "")
            },
            onShareClick = {
                context.shareNews(article?.url ?: "")
            },
            onBookMarkClick = {
                if (state.isBookMarked) {
                    onEvent(NewsEvent.DeleteNews(article!!))
                    onEvent(NewsEvent.RemoveBookMark)
                } else {
                    onEvent(NewsEvent.SaveNews(article!!))
                    onEvent(NewsEvent.SaveBookMark)
                }
            },
            state = state
        )


        LazyColumn(
            contentPadding = PaddingValues(
                start = 11.dp, end = 11.dp, top = 11.dp
            )
        ) {

            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article?.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(11.dp))


                Text(
                    text = article?.title ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article?.content ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }


        }

    }


}










