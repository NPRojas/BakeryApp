package com.example.bakeryapp.data

import android.content.Context
import com.example.bakeryapp.R
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson


// This is a mock repository as this is app is meant for front end practice

class MenuRepository(private val context: Context) {
//
//    private val menuItems = listOf(
//        MenuItem(0, "Iced Coffee", 2.99, R.drawable.coffee),
//        MenuItem(1, "Bagel with Cream Cheese", 4.75, R.drawable.bagel),
//        MenuItem(2, "Croissant Sandwich", 5.50, R.drawable.croissant),
//        MenuItem(3, "Rose Macaron", 2.99, R.drawable.rose_macaron),
//        MenuItem(4, "Chocolate Chip Walnut Cookie", 4.00, R.drawable.chocolate_chip_walnut_cookies),
//        MenuItem(5, "Cream Puff", 5.50, R.drawable.cream_puffs),
//        MenuItem(6, "Apple Cinnamon Danish", 5.25, R.drawable.apple_cinnamon_danish),
//        MenuItem(7, "Raspberry Rugelach", 3.50, R.drawable.raspberry_rugelach),
//        MenuItem(8, "Blueberry Scone", 4.25, R.drawable.blueberry_scones)
//    )
    private val gson = Gson()

    val currentOrder = mutableListOf<OrderItem>()

    val processedMenuItems = mutableListOf<String>()

    private fun loadMenuAssets(): String {
        return context.assets.open("menu.json")
            .bufferedReader().use {
                it.readText()
            }
    }

    fun getMenuItems(): List<MenuItem> {
        val json = loadMenuAssets()
        return gson.fromJson(json, object : TypeToken<List<MenuItem>>() {}.type)
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

    fun getOrderTotalPrice(): Double {
        return currentOrder.sumOf { it.menuItem.price * it.quantity }
    }

    fun deleteOrder() {
        currentOrder.clear()
        processedMenuItems.clear()
    }

    //TODO: add these funtions to the viewmodel

    fun deleteMenuItemFromOrder(orderItem: OrderItem) {
        // find the menu item in the current order and delete it
        val itemInOrder = currentOrder.find { it.menuItem == orderItem.menuItem}
        currentOrder.remove(itemInOrder)
    }

    fun retriveRewardsPoints() {
        // get reward points from the database
    }

    fun calculateNewRewardsPoints(totalPrice: Double): Double {
        val rewardPoints = totalPrice.times(11)
        return rewardPoints
    }

    fun convertRewardPointsToCash(points: Int) {

    }
}

