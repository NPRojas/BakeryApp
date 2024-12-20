package com.example.bakeryapp.data

data class OrderItem(
    val menuItem: MenuItem,
    val quantity: Int,
    val individualItemId: String = generateUniqueId(menuItem.name)
){
    companion object {
        fun generateUniqueId(name: String): String {
            return "${name}_${System.currentTimeMillis()}"
        }
    }
}
