package com.example.bakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.bakeryapp.ui.MenuViewModel
import com.example.bakeryapp.ui.Navigation
import com.example.bakeryapp.ui.MenuScreen
import com.example.bakeryapp.ui.theme.BakeryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BakeryAppTheme {
                Navigation()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Preview() {
    BakeryAppTheme {

    }
}

