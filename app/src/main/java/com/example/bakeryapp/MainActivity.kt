package com.example.bakeryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bakeryapp.ui.MenuViewModel
import com.example.bakeryapp.ui.MenuViewModelFactory
import com.example.bakeryapp.ui.Navigation
import com.example.bakeryapp.ui.btmNav.BottomNav
import com.example.bakeryapp.ui.theme.BakeryAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // note to self: navController is best declared in the main file to pass along to the composable that need it
            val navController = rememberNavController()
            BakeryAppTheme {
                val menuViewModel: MenuViewModel = viewModel(
                    factory = MenuViewModelFactory(applicationContext)
                )

                Scaffold(
                    bottomBar = { BottomNav(navController) }
                ) { innerPadding ->
                    Navigation(
                        navController,
                        menuViewModel,
                        Modifier.padding(innerPadding)
                    )
                }

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

