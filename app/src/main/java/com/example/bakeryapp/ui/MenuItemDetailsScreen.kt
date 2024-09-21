package com.example.bakeryapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakeryapp.data.OrderItem

@Composable
fun MenuItemDetailsScreen(
    viewModel: MenuViewModel,
    menuItemId: Int,
    navController: NavController){

    // get the menu items details
    val menuItem = viewModel.getMenuItemById(menuItemId)

    // keep track of quantity
    var quantity by remember {
        mutableStateOf(1)
    }

    // display the item info
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(painter = painterResource(id = menuItem.img) , contentDescription = menuItem.name)
        Text(text = menuItem.name, style = MaterialTheme.typography.titleSmall)
        Text(text = "$${menuItem.price}", style = MaterialTheme.typography.bodySmall)
    }

    // TODO: Missing a quantity btn
    Spacer(modifier = Modifier.height(15.dp))

    // add to Order Btn
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            val order = OrderItem(menuItem, quantity)
            viewModel.addToOrder(order)
        },
    ) {
        Text(text = "Add to Order", style = MaterialTheme.typography.labelMedium )
    }




}