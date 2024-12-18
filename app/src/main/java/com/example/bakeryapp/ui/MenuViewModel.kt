package com.example.bakeryapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bakeryapp.data.FirestoreRepository
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.data.MenuRepository
import com.example.bakeryapp.data.OrderItem
import com.example.bakeryapp.data.User
import com.example.bakeryapp.presentation.sign_in.vol1.SignInResult
import com.example.bakeryapp.presentation.sign_in.vol1.SignInState
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MenuViewModel(): ViewModel() {
    private val userRepository = FirestoreRepository()

    fun addUser (user: User) {
        userRepository.addUser(user)
    }

    fun updatePoints(userID: String, updatedPoints: Int){
        userRepository.updatePoints(userID, updatedPoints)
    }

    fun retriveUser() {
        userRepository.retriveUser("1")
    }

    //--------------------------

    private val repository = MenuRepository()

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

    fun deleteMenuItem(orderItem: OrderItem) {
        repository.deleteMenuItemFromOrder(orderItem)
    }
    // These functions might be better placed in another view model in the future

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()



}