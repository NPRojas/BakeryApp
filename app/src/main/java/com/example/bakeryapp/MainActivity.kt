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
import com.example.bakeryapp.ui.Navigation
import com.example.bakeryapp.ui.btmNav.BottomNav
import com.example.bakeryapp.ui.theme.BakeryAppTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    //-------FIREBASE UI START-----------------------------------

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    private fun startSignIn() {
        // Chose auth providers
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        // Create and launch sign in Intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.Theme_BakeryApp)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
    //-------FIREBASE UI END-----------------------------------


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // note to self: navController is best declared in the main file to pass along to the composable that need it
            val navController = rememberNavController()
            BakeryAppTheme {
                val menuViewModel: MenuViewModel = viewModel()

                Scaffold(
                    bottomBar = { BottomNav(navController) }
                ) { innerPadding ->
                    Navigation(
                        navController,
                        menuViewModel,
                        Modifier.padding(innerPadding),
                        { startSignIn() }
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

