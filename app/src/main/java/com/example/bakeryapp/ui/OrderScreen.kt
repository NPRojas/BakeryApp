package com.example.bakeryapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bakeryapp.ui.order.OrderItemCard

@Composable
fun OrderScreen(viewModel: MenuViewModel) {
    val orderItems = viewModel.getCurrentOrder()
    val orderTotal= viewModel.getOrderTotalPrice()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        Text("Order Details", style = MaterialTheme.typography.titleSmall)
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
                }
            }

            Text(text = "Total Price: $${orderTotal}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

