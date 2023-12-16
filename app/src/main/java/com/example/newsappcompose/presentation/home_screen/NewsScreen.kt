package com.example.newsappcompose.presentation.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsappcompose.R
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappcompose.presentation.common.ArticleList
import com.example.newsappcompose.presentation.common.SearchBar
import com.example.newsappcompose.presentation.navigation.Screens
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.presentation.state_event.NewsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    newsState: NewsState,
    navController: NavController,
    ) {



    val articles = newsState.articles.collectAsLazyPagingItems()

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .statusBarsPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = 6.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navController.navigate(Screens.SearchScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(8.dp))

        ArticleList(
            modifier = Modifier.padding(horizontal = 8.dp),
            articles = articles,
            onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "article",
                    value = it
                )
                navController.navigate(Screens.DetailsScreen.route)
            }
        )


    }

}













