package com.example.bakeryapp

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    //object MenuItemDetailsScreen: Screen("menu_item_details_screen/$menuItemId")
    fun createRoute(menuItemId: String) = "menu_item_details_screen/$menuItemId"
}