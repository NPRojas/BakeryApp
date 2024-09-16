package com.example.bakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.ui.theme.BakeryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BakeryAppTheme {
            }
        }
    }
}


@Composable
fun MenuItemCard (item: MenuItem) {
    Card {
        Column {
            Image(
                painter = painterResource(id =R.drawable.ic_launcher_background),
                contentDescription = item.name,
                modifier = Modifier.size(100.dp))
            Text(text = item.name)
            Text(text = "$${item.price}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuItemCard() {
    BakeryAppTheme {
        MenuItemCard(item = MenuItem(name = "Sample Item", price = 10.0, imgUrl = null))
    }
}