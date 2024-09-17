package com.example.bakeryapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakeryapp.ui.menu.MenuHeader
import com.example.bakeryapp.ui.menu.MenuItemCard


@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel
) {
    // retrieve the menu items from vm
    val menuItems = viewModel.menuItems
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        MenuHeader(title = "Menu")
        
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(menuItems) {menuItem ->
                MenuItemCard(menuItem) {
                    navController.navigate("menu_item_details_screen/${menuItem.id}")
                }
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {

}
