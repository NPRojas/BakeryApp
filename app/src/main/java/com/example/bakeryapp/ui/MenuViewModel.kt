package com.example.bakeryapp.ui

import androidx.lifecycle.ViewModel
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.data.MenuRepository
import com.example.bakeryapp.data.OrderItem
import com.example.bakeryapp.presentation.sign_in.vol1.SignInResult
import com.example.bakeryapp.presentation.sign_in.vol1.SignInState
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MenuViewModel(): ViewModel() {
    //--------------------------

    //--------------------------

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