package com.example.bakeryapp.ui.order

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bakeryapp.data.OrderItem

@Composable
fun OrderItemCard(orderItem: OrderItem) {
    Row {
        Text(text = "${orderItem.menuItem.name} x ${orderItem.quantity}" )
        Text(text = "${orderItem.menuItem.price * orderItem.quantity}")
    }
}