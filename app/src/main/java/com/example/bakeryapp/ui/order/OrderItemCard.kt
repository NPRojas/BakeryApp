package com.example.bakeryapp.ui.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bakeryapp.data.OrderItem

@Composable
fun OrderItemCard(orderItem: OrderItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Red),
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(85.dp)
                    .clip(CircleShape),
                painter = painterResource(id = orderItem.menuItem.img),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier
                    .weight(1f), horizontalAlignment = Alignment.Start
            ) {
                Text(text = "${orderItem.menuItem.name} x ${orderItem.quantity}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "$${orderItem.menuItem.price * orderItem.quantity}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}