package com.example.newsappcompose.presentation.favorite_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappcompose.domain.model.Article


@Composable
fun FavoriteCard(
    article: Article,
    onArticleClick: (Article) -> Unit
) {

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxWidth().clickable { onArticleClick(article) }) {

        AsyncImage(
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .background(Color.DarkGray)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text(
                text = article.content,
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 2
            )
        }
    }


}




