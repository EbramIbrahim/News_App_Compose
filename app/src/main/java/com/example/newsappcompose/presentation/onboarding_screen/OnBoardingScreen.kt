package com.example.newsappcompose.presentation.onboarding_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsappcompose.presentation.common.NewsBackButton
import com.example.newsappcompose.presentation.common.NewsButton
import com.example.newsappcompose.presentation.common.PageIndicator
import com.example.newsappcompose.presentation.navigation.Screens
import com.example.newsappcompose.presentation.state_event.NewsEvent
import com.example.newsappcompose.utils.Constant.onBoardingList
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onEvent: (NewsEvent) -> Unit,
    navController: NavController
) {

    Column(modifier = Modifier.fillMaxSize()) {

        val pagerState = rememberPagerState(initialPage = 0) {
            onBoardingList.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("back", "Next")
                    2 -> listOf("back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingCardInfo(onBoarding = onBoardingList[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PageIndicator(
                pageSize = onBoardingList.size,
                selectedPage = pagerState.currentPage,
                modifier = Modifier.padding(2.dp)
            )


            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(3.dp)) {

                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()){
                    NewsBackButton(text = buttonState.value[0]) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }
                NewsButton(text = buttonState.value[1]) {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            onEvent(NewsEvent.SaveUserEntry)
                            navController.navigate(Screens.HomeScreen.route)
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }

        }

    }

}












