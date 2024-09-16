package com.example.bakeryapp.data

import com.example.bakeryapp.R

// This is a mock repository as this is app is meant for front end practice

object MenuRepository {
    fun getMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem("Hot Coffee", 2.99, R.drawable.croissant),
            MenuItem("Bagel with Cream Cheese", 4.75, R.drawable.croissant),
            MenuItem("Croissant", 5.50, R.drawable.croissant)
        )
    }
}