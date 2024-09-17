package com.example.bakeryapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bakeryapp.Screen
import com.example.bakeryapp.data.MenuViewModel
import com.example.bakeryapp.ui.menu.MenuScreen

@Composable
fun Navigation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MenuScreen(navController, MenuViewModel())
        }
        composable(route = Screen.MenuItemDetailsScreen.route) {
            MenuItemDetailsScreen(navController)
        }
    }
}


