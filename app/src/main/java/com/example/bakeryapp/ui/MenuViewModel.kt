package com.example.bakeryapp.ui

import android.app.Application
import android.credentials.GetCredentialException
import android.credentials.GetCredentialRequest
import android.credentials.GetCredentialResponse
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.data.MenuRepository
import com.example.bakeryapp.data.Order
import com.example.bakeryapp.data.OrderItem
import com.example.bakeryapp.presentation.sign_in.SignInResult
import com.example.bakeryapp.presentation.sign_in.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(): ViewModel() {

    private val repository = MenuRepository

    val menuItems = repository.getMenuItems()

    fun getCurrentOrder(): MutableList<OrderItem> {
        return repository.currentOrder
    }

    fun getMenuItemById(itemId: Int): MenuItem {
        return repository.getMenuItemById(itemId)
    }

    fun addToOrder(orderItem: OrderItem) {
        repository.addToOrder(orderItem)
    }

    fun getOrderTotalPrice(): Double {
        return repository.getOrderTotalPrice()
    }

    fun deleteOrder() {
        repository.deleteOrder()
    }

    // These functions might be better placed in another view model in the future

    var _isLoggedInState = MutableStateFlow(SignInState())
    val isLoggedInState = _isLoggedInState.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _isLoggedInState.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        )
        }
    }

    fun resetState() {
        _isLoggedInState.update { SignInState() }
    }
}