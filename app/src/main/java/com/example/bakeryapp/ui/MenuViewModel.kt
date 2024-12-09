package com.example.bakeryapp.ui

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

    var isLoggedIn = mutableStateOf(false)
}