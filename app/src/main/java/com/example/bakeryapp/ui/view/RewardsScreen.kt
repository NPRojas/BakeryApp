package com.example.bakeryapp.ui.view

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.bakeryapp.ui.theme.onPrimaryContainerDark
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.primaryContainerLight
import com.example.bakeryapp.ui.theme.primaryLight
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
fun setUpFirebase() {
    val firestoredb = Firebase.firestore
    firestoredb.useEmulator("10.0.2.2", 8080)

    firestoredb.firestoreSettings = firestoreSettings {
        isPersistenceEnabled = false
    }

    // Create a new user with a first and last name
    val user = hashMapOf(
        "first" to "Ada",
        "last" to "Lovelace",
        "points" to 0
    )

    firestoredb.collection("users")
        .add(user)
        .addOnSuccessListener { documentReference ->
            Log.d("LEMON", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener{e ->
            Log.w("LEMON", "Error adding document", e)
        }
}

@Composable
fun RewardsScreen(menuViewModel: MenuViewModel) {

    val isLoggedIn by menuViewModel.isLoggedIn.collectAsState()

    Column {
        MenuHeader(title = "Rewards")

        if (!isLoggedIn) {
            RedeemRewards(556)
        } else {
            GoogleSignInButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BakeryAppTheme {
        RedeemRewards(100)
    }
}

@Composable
fun RedeemRewards(points: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box (
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .height(150.dp)
                .background(primaryContainerLight)
                .border(
                    width = 2.dp,
                    color = onPrimaryContainerLight,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center

        ) {
            Text("My Points \n $points",
                style = MaterialTheme.typography.labelLarge,
                color = primaryLight,
                textAlign = TextAlign.Center)
        }
        
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = onPrimaryContainerLight),
            modifier = Modifier.offset(24.dp),
            //TODO Implement a nav to the main screen
            onClick = {}) {
            Text(text = "Redeem Rewards", style = MaterialTheme.typography.titleMedium, color = onPrimaryLight)
        }
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
            // TODO: Make this a string resource and introduce nonce
            .setServerClientId("1062815894109-tlfcav4p4p7qu91174sdqv3qihupieka.apps.googleusercontent.com")
            .setAutoSelectEnabled(false)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
            try{
                val response = credentialManager.getCredential(
                    request = request,
                    context = context
                )
                val credential = response.credential

                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(credential.data)

                val googleIdToken = googleIdTokenCredential.idToken

                Log.i(TAG, googleIdToken)
                Toast.makeText(context, "You are signed in!", Toast.LENGTH_LONG).show()

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