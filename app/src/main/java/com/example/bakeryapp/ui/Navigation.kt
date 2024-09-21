package com.example.bakeryapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bakeryapp.Screen


@Composable
fun Navigation(viewModel: MenuViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu_screen") {
        composable("menu_screen") {
            MenuScreen(navController, viewModel)
        }

        composable(
            route = "menu_item_details_screen/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val menuItemId =
                navBackStackEntry.arguments?.getInt("itemId")!!
            MenuItemDetailsScreen(viewModel = viewModel, menuItemId = menuItemId, navController)
        }

    }
}


