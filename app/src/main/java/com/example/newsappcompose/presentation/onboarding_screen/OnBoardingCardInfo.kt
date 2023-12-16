package com.example.newsappcompose.presentation.onboarding_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.OnBoarding
import com.example.newsappcompose.utils.Constant.onBoardingList

@Composable
fun OnBoardingCardInfo(
    onBoarding: OnBoarding
) {

    Column {

        Image(
            painter = painterResource(id = onBoarding.image),
            contentDescription = onBoarding.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = onBoarding.title,
            color = colorResource(id = R.color.display_small),
            modifier = Modifier.padding(horizontal = 11.dp),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = onBoarding.description,
            color = colorResource(id = R.color.text_medium),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 11.dp),

        )
    }


}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingCardInfo(onBoarding = onBoardingList[0])
}










