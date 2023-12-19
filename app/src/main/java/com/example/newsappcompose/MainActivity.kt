package com.example.newsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.model.BottomNavItem
import com.example.newsappcompose.presentation.NewsViewModel
import com.example.newsappcompose.presentation.common.BottomNavigation
import com.example.newsappcompose.presentation.navigation.Screens
import com.example.newsappcompose.presentation.navigation.SetUpNavGraph
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NewsAppComposeTheme {
                val viewModel: NewsViewModel = hiltViewModel()
                val appEntryState = viewModel.startScreenState
                val navController = rememberNavController()

                installSplashScreen().apply {
                    setKeepOnScreenCondition {
                        viewModel.splashCondition
                    }
                }

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                
                
                Scaffold( bottomBar = {
                    BottomNavigation(
                        items = listOf(
                            BottomNavItem(
                                title = "Home",
                                route = Screens.HomeScreen.route,
                                icon = Icons.Default.Home
                            ),
                            BottomNavItem(
                                title = "Favorite",
                                route = Screens.BookMarkScreen.route,
                                icon = Icons.Default.Favorite
                            ),

                        ),
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }
                ) {
                    SetUpNavGraph(
                        startDestination = appEntryState,
                        onEvent = viewModel::onEvent,
                        state = viewModel.newsState.collectAsState().value,
                        navHostController = navController,
                        modifier = Modifier.padding(it)
                    )
                }



            }
        }
    }
}

