package com.example.bakeryapp.data

import com.example.bakeryapp.R

// This is a mock repository as this is app is meant for front end practice

object MenuRepository {

    private val menuItems = listOf(
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

    val currentOrder = mutableListOf<OrderItem>()

    fun getMenuItems(): List<MenuItem> {
        return menuItems
    }

    fun getMenuItemById(itemId: Int): MenuItem {
        return getMenuItems().find { it.id == itemId }
            ?: throw IllegalArgumentException("MenuItem with id $itemId is not found")
    }

    fun createOrderItem(itemId: Int, itemQuantity: Int): OrderItem {
        val menuItem = getMenuItemById(itemId)
        val orderItem = OrderItem(menuItem,itemQuantity)
        return orderItem
    }

    fun addToOrder(orderItem: OrderItem) {
        currentOrder.add(orderItem)
    }

    fun getOrder() : List<OrderItem> {
        return currentOrder
    }
}