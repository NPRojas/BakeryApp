package com.example.bakeryapp.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakeryapp.ui.MenuViewModel
import com.example.bakeryapp.ui.menu.MenuHeader
import com.example.bakeryapp.ui.order.OrderItemCard
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.primaryLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.pow

@Composable
fun OrderScreen(viewModel: MenuViewModel, navController: NavController) {
    val orderItems = viewModel.getCurrentOrder()
    val pointsUsed = viewModel.pointsUsedForOrder.collectAsState().value
    val newPointsEarned = viewModel.newPointsFromOrder.collectAsState().value
    val orderTotal = viewModel.discountPointsFromTotalOrder(pointsUsed)
    val discountTotal = viewModel.totalDiscount.collectAsState().value
    val userId = viewModel.userId.collectAsState().value


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
                    OrderItemCard(orderItem = orderItem) { viewModel.deleteMenuItem(orderItem) }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row() {
                Text(text = "Discount Total:", style = MaterialTheme.typography.titleMedium, color = primaryLight)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "$${discountTotal}", style = MaterialTheme.typography.titleMedium, color = primaryLight)}

            Row() {
                Text(text = "Total Price:", style = MaterialTheme.typography.titleMedium, color = primaryLight)
                Spacer(modifier = Modifier.weight(1f))
                val df = DecimalFormat("#.##")
                Text(text = "$${df.format(orderTotal)}", style = MaterialTheme.typography.titleMedium, color = primaryLight)}
            }

        Spacer(modifier = Modifier.weight(1f))

        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(context, "Order Received!", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.updatePoints(userId, newPointsEarned)
            }
            viewModel.deleteOrder()
            navController.navigate("menu_screen")
        },
            colors = ButtonDefaults.buttonColors(containerColor = onPrimaryContainerLight),
            modifier = Modifier
            .fillMaxWidth()) {
            Text(text = "Pay Now", style = MaterialTheme.typography.titleMedium, color = onPrimaryLight)
        }
    }
}
