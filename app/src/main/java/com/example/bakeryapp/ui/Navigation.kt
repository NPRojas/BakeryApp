package com.example.bakeryapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bakeryapp.ui.view.MenuItemDetailsScreen
import com.example.bakeryapp.ui.view.MenuScreen
import com.example.bakeryapp.ui.view.OrderScreen
import com.example.bakeryapp.ui.view.RewardsScreen


@Composable
fun Navigation(
    navCon: NavHostController,
    viewModel: MenuViewModel,
    modifier: Modifier,
    startSignIn: () -> Unit ) {

    NavHost(navController = navCon, startDestination = "menu_screen", modifier = modifier) {

        composable("menu_screen") {
            MenuScreen(navCon, viewModel)
        }

        composable(
            route = "menu_item_details_screen/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val menuItemId =
                navBackStackEntry.arguments?.getInt("itemId")!!
            MenuItemDetailsScreen(viewModel = viewModel, menuItemId = menuItemId, navCon)
        }

        composable(route = "order_screen"){
            OrderScreen(viewModel = viewModel, navCon)
        }

        composable(route = "rewards_screen") {
            RewardsScreen(viewModel, onSignInClick = { startSignIn() })
        }
    }
}



