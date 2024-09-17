package com.example.bakeryapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun MenuItemDetailsScreen(viewModel: MenuViewModel, menuItemId: Int){

    val menuItem = viewModel.getMenuItemById(menuItemId)

    Column {
        Image(painter = painterResource(id = menuItem.img) , contentDescription = menuItem.name)
        Text(text = menuItem.name)
        Text(text = "$${menuItem.price}")
    }


}