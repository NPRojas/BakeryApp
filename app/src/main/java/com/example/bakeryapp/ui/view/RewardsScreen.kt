package com.example.bakeryapp.ui.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bakeryapp.R
import com.example.bakeryapp.presentation.sign_in.vol1.GoogleAuthUiClient
import com.example.bakeryapp.presentation.sign_in.vol1.SignInScreen
import com.example.bakeryapp.ui.MenuViewModel
import com.example.bakeryapp.ui.menu.MenuHeader
import com.example.bakeryapp.ui.theme.BakeryAppTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(menuViewModel: MenuViewModel, onSignInClick: () -> Unit) {

    val context = LocalContext.current
    val googleAuthUiClient = remember {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val isLoggedIn = menuViewModel.isLoggedInState.value.isSignInSuccessful

    val coroutineScope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    val signInResult = googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    menuViewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    val state by menuViewModel._isLoggedInState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(
                context,
                "Sign in successful",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column {
        MenuHeader(title = "Rewards")

        if (isLoggedIn) {
            Text("Welcome to the Rewards Screen!")

            Button(onClick = {  }) {
                Text("Redeem Rewards")
            }
        } else {
//            SignInScreen(
//                state = state,
//                onSignInClick = {
//                    // Google Sign-In
//                    coroutineScope.launch {
//                        val signInIntentSender = googleAuthUiClient.signIn()
//                        launcher.launch(
//                            IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build()
//                        )
//                    }
//                }
//            )
            GoogleSignInButton()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BakeryAppTheme {
//        RewardsScreen(menuViewModel = MenuViewModel(), onSignInClick = onSignInCl)
    }
}

@Composable
fun GoogleSignInButton() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val onClick:() -> Unit = {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            // TODO: Make this a string resource
            .setServerClientId("1062815894109-tlfcav4p4p7qu91174sdqv3qihupieka.apps.googleusercontent.com")
            .setAutoSelectEnabled(false)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            try{
                val request = credentialManager.getCredential(
                    request = request,
                    context = context
                )
                val credential = request.credential

                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(credential.data)

                val googleIdToken = googleIdTokenCredential.idToken

                Log.i(TAG, googleIdToken)
                Toast.makeText(context, "You re signed in!", Toast.LENGTH_LONG).show()

            } catch(e: GetCredentialException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    Button(onClick = onClick) {
        Text("Sign in with Google")
    }
}