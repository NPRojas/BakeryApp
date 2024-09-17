package com.example.bakeryapp.ui

import androidx.lifecycle.ViewModel
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.data.MenuRepository

class MenuViewModel: ViewModel() {
    val menuItems = MenuRepository.getMenuItems()

    fun getMenuItemById(itemId: Int) : MenuItem {
        return menuItems.find { it.id == itemId }
            ?: throw IllegalArgumentException("MenuItem with id $itemId is not found")
    }
}