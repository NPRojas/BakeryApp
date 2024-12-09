package com.example.bakeryapp.ui.btmNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

// TODO: Better organize the imports for the icons
@Composable
fun BottomNav(navCon: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Menu", "Rewards", "More")
    val selectedIcons = listOf(Icons.AutoMirrored.Filled.ListAlt, Icons.Filled.Star, Icons.Filled.MoreHoriz)
    val unselectedIcons =
        listOf(Icons.AutoMirrored.Outlined.ListAlt, Icons.Outlined.Star, Icons.Outlined.MoreHoriz)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navCon.navigate("${items[selectedItem].lowercase()}_screen")
                }
            )
        }
    }
}
