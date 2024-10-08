package com.example.bakeryapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bakeryapp.data.OrderItem
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.onSecondaryContainerLight
import com.example.bakeryapp.ui.theme.primaryLight

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
            .fillMaxWidth()
    ) {
        Image(modifier = Modifier
            .fillMaxWidth(),
            painter = painterResource(id = menuItem.img) , contentDescription = menuItem.name )

        Spacer(modifier = Modifier.height(10.dp))

        Text(modifier = Modifier.padding(horizontal = 5.dp), text = menuItem.name, style = MaterialTheme.typography.bodyLarge, color = primaryLight)

        Spacer(modifier = Modifier.height(2.dp))

        Text(modifier = Modifier.padding(horizontal = 5.dp), text = "$${menuItem.price}", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

        Spacer(modifier = Modifier.weight(1f))

        // TODO: Missing a quantity btn
        // add to Order Btn
        Button(onClick = {
            val order = OrderItem(menuItem, quantity)
            viewModel.addToOrder(order)
            navController.popBackStack()},
            colors = ButtonDefaults.buttonColors(containerColor = onPrimaryContainerLight),
            modifier = Modifier
            .fillMaxWidth()) {
            Text(text = "Add to Order", style = MaterialTheme.typography.titleMedium, color = onPrimaryLight,)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {

}
