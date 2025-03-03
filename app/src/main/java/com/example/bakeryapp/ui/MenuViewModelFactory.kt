package com.example.bakeryapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bakeryapp.data.MenuRepository

class MenuViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(MenuRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
