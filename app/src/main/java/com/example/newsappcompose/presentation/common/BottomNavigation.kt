package com.example.newsappcompose.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsappcompose.domain.model.BottomNavItem

@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {

    val entry = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = Color.DarkGray,
        tonalElevation = 4.dp
    ) {

        items.forEach { item ->

            NavigationBarItem(
                selected = item.route == entry.value?.destination?.route,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color.Black,
                    selectedIconColor = Color.Black,
                    unselectedTextColor = Color.White,
                    unselectedIconColor = Color.White
                ),

                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )

                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
        )
    }


}

}









