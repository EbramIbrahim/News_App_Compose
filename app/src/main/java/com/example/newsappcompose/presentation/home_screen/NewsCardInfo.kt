package com.example.newsappcompose.presentation.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.Article

@Composable
fun NewsCardInfo(
    article: Article,
    onClick: () -> Unit
) {


    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(11.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = article.description,
                contentScale = ContentScale.Crop,
            )
        }


        Column(
            modifier = Modifier.padding(3.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = colorResource(id = R.color.text_medium),
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(3.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(11.dp),
                    colorResource(id = R.color.body)
                )

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),

                    )
            }
        }

    }


}










