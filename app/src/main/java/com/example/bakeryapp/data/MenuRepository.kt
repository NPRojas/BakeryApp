package com.example.bakeryapp.data

import com.example.bakeryapp.R

// This is a mock repository as this is app is meant for front end practice

object MenuRepository {
    fun getMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem(0,"Hot Coffee", 2.99, R.drawable.croissant),
            MenuItem(1,"Bagel with Cream Cheese", 4.75, R.drawable.croissant),
            MenuItem(2,"Croissant", 5.50, R.drawable.croissant),
            MenuItem(3,"Hot Coffee", 2.99, R.drawable.croissant),
            MenuItem(4,"Bagel with Cream Cheese", 4.75, R.drawable.croissant),
            MenuItem(5,"Croissant", 5.50, R.drawable.croissant),
            MenuItem(6,"Croissant", 5.50, R.drawable.croissant),
            MenuItem(7,"Hot Coffee", 2.99, R.drawable.croissant),
            MenuItem(8,"Bagel with Cream Cheese", 4.75, R.drawable.croissant),
            MenuItem(9,"Croissant", 5.50, R.drawable.croissant)
        )
    }
}