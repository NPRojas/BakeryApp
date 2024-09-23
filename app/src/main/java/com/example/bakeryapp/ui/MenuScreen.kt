package com.example.bakeryapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakeryapp.ui.menu.MenuHeader
import com.example.bakeryapp.ui.menu.MenuItemCard
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.primaryContainerLight


@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel
) {
    // retrieve the menu items from vm
    val menuItems = viewModel.menuItems
    Column(
        modifier = Modifier
            .background(onPrimaryLight)
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

        if(viewModel.getCurrentOrder().isNotEmpty()) {
            Button(onClick = {navController.navigate("order_screen")},
                colors = ButtonDefaults.buttonColors(containerColor = onPrimaryContainerLight),
                modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "View Order", style = MaterialTheme.typography.titleMedium, color = onPrimaryLight)
            }
        }
        
    }
}

