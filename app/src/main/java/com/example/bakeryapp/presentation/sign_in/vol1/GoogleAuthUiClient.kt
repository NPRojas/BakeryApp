package com.example.bakeryapp.presentation.sign_in.vol1

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.example.bakeryapp.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: ApiException) {
            e.printStackTrace()
            if (e.statusCode == 16) {
                // Handle "Cannot find a matching credential" error
                Log.e("GoogleSignIn", "No credentials found for sign-in.")
            } else {
                // Handle other exceptions
                Log.e("GoogleSignIn", "Sign-in failed: ${e.message}")
            }
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
       val credential = oneTapClient.getSignInCredentialFromIntent(intent)
       val googleIdToken = credential.googleIdToken
       val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

       return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
           SignInResult(
               data = user?.run{
                   UserData(
                       userId = uid,
                       username = displayName,
                       profilePictureUrl = photoUrl?.toString()
                   )
               },
               errorMessage = null
           )
       } catch (e: Exception) {
           e.printStackTrace()
           if (e is CancellationException) throw e
           SignInResult(
               data = null,
               errorMessage = e.message
           )
       }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e:Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw  e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}