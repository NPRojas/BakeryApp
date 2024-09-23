package com.example.bakeryapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakeryapp.data.OrderItem
import com.example.bakeryapp.ui.menu.MenuHeader
import com.example.bakeryapp.ui.order.OrderItemCard
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.primaryLight

@Composable
fun OrderScreen(viewModel: MenuViewModel, navController: NavController) {
    val orderItems = viewModel.getCurrentOrder()
    val orderTotal= viewModel.getOrderTotalPrice()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        MenuHeader(title = "Order Details")
        Spacer(modifier = Modifier.height(15.dp))

        if (orderItems.isEmpty()) {
            Text(text = "Empty cart")
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(orderItems) { orderItem ->
                    OrderItemCard(orderItem = orderItem)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row() {
                Text(text = "Total Price:", style = MaterialTheme.typography.titleMedium, color = primaryLight)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "$${orderTotal}", style = MaterialTheme.typography.titleMedium, color = primaryLight)}
            }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = onPrimaryContainerLight),
            modifier = Modifier
            .fillMaxWidth()) {
            Text(text = "Pay Now", style = MaterialTheme.typography.titleMedium, color = onPrimaryLight)
        }
    }
}
