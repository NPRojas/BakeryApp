package com.example.bakeryapp.data

data class Order(
    val orderId: Int,
    val items: MutableList<OrderItem> = mutableListOf()
)
