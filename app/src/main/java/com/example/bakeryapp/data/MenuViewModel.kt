package com.example.bakeryapp.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MenuViewModel: ViewModel() {
    val menuItems = MenuRepository.getMenuItems()
}