package com.example.bakeryapp.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakeryapp.data.User
import com.example.bakeryapp.ui.MenuViewModel
import com.example.bakeryapp.ui.menu.MenuHeader
import com.example.bakeryapp.ui.menu.MenuItemCard
import com.example.bakeryapp.ui.theme.BakeryAppTheme
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryDark
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.primaryContainerDark
import com.example.bakeryapp.ui.theme.primaryContainerLight
import com.example.bakeryapp.ui.theme.primaryLight
import com.example.bakeryapp.ui.theme.secondaryContainerDark
import com.example.bakeryapp.ui.theme.secondaryContainerLight


@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel
) {
    // retrieve the menu items from vm
    val menuItems = viewModel.menuItems
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val points by viewModel.points.collectAsState()
    val tempPoints by viewModel.tempPoints.collectAsState()

    Column(
        modifier = Modifier
            .background(onPrimaryLight)
            .fillMaxSize()
            .padding(16.dp)
    ){
        MenuHeader(title = "Menu")
        //TODO implement a box for rewards if logged in

        if (isLoggedIn && viewModel.getCurrentOrder().isNotEmpty()) {
            RewardsBar(tempPoints)
        } else if (isLoggedIn) {
            viewModel.updateTempPoints(points)
            RewardsBar(points)
        }

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

@Preview(showBackground = true)
@Composable
fun Menu() {
    BakeryAppTheme {
        RewardsBar(343)
    }
}

@Composable
fun RewardsBar(points: Int?) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = primaryContainerLight
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rewards",
                style = MaterialTheme.typography.bodyLarge,
                color = primaryLight,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = secondaryContainerDark,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$points points",
                    style = MaterialTheme.typography.bodyLarge,
                    color = primaryLight,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
