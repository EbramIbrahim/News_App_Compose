package com.example.newsappcompose.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.presentation.home_screen.NewsCardInfo


@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick:(Article) -> Unit
) {

    val handlePagingResult = handlePagingResult(articles = articles)

    // if handlePagingResult means no error & not loading


    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(all = 3.dp)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    NewsCardInfo(article = article, onClick = { onClick(article) })
                }
            }

        }
    }

}



@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {

    val loadState = articles.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }
        else -> true

    }

}





@Composable
fun ShimmerEffect() {
    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.spacedBy(8.dp)){
        repeat(20){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}