package com.example.bakeryapp.ui

import androidx.lifecycle.ViewModel
import com.example.bakeryapp.data.FirestoreRepository
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.data.MenuRepository
import com.example.bakeryapp.data.OrderItem
import com.example.bakeryapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuViewModel(): ViewModel() {
    private val userRepository = FirestoreRepository()

    fun addUser (user: User) {
        userRepository.addUser(user)
    }

    suspend fun updatePoints(userID: String, updatedPoints: Int){
        userRepository.updatePoints(userID, updatedPoints)
    }

    suspend fun getPoints(userId: String) : Int {
        return userRepository.getPoints(userId)
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

    private val _points = MutableStateFlow(0)
    val points: StateFlow<Int> = _points.asStateFlow()

    private val _tempPoints = MutableStateFlow(0)
    val tempPoints : StateFlow<Int> = _tempPoints.asStateFlow()

    private val _pointsUsedForOrder = MutableStateFlow(0)
    val pointsUsedForOrder : StateFlow<Int> = _pointsUsedForOrder.asStateFlow()

    private val _newPointsFromOrder = MutableStateFlow(0)
    val newPointsFromOrder : StateFlow<Int> = _newPointsFromOrder.asStateFlow()

    private val _totalDiscount = MutableStateFlow(0)
    val totalDiscount : StateFlow<Int> = _totalDiscount.asStateFlow()

    fun updateLocalPoints(points: Int) {
            _points.value = points
    }

    fun updateTempPoints(points: Int) {
        _tempPoints.value = points
    }

    fun updatedPointsUsed(points: Int) {
        _pointsUsedForOrder.value = points
    }

    fun updateTotalDiscount(discount: Int) {
        _totalDiscount.value = discount
    }

    fun checkLoggedIn(status : Boolean)  {
        _isLoggedIn.value = status
    }


    //TODO: Fix the calculation on the function
    fun useRewardPoints() {
        val startingPoints = points.value
        val tempPoints = tempPoints.value.toDouble()
        // Convert points to cash
        var rewardMoney = tempPoints / 100

        val currentOrder = getCurrentOrder()
        currentOrder.forEach { order ->
            val itemPrice = order.menuItem.price.toDouble()

            if (rewardMoney > 0) {
                if (itemPrice <= rewardMoney) {
                   rewardMoney -= itemPrice
                } else {
                    rewardMoney = 0.0
                }
            }
        }

        // Calculate and update the points used for the current order
        val leftoverPoints = (rewardMoney*100).toInt()
        val pointsUsedForOrder = (startingPoints - rewardMoney).toInt()
        updatedPointsUsed(pointsUsedForOrder)
        updateTempPoints(leftoverPoints)
    }


    fun discountPointsFromTotalOrder(pointsUsedForOrder: Int): Double {
        if (points.value == 0) {
            return getOrderTotalPrice()
        } else {
            var newOrderTotal: Double? = null
            // get the item total price
            val orderTotal = getOrderTotalPrice()
            // get the amount of reward points used for the order
            val pointsUsed = pointsUsedForOrder
            // convert the points to money to the nearest dollar
            val rewardMoney = pointsUsed.div(100)
            updateTotalDiscount(rewardMoney)
            // subtract the points from the total price
            newOrderTotal = orderTotal - rewardMoney
            val points = points
            var newPointsTotal = points.value.minus(pointsUsed)
            // see how much money is being spend and calculate rewards points for htst
            val pointsEarnedFromOrder = repository.calculateNewRewardsPoints(newOrderTotal)?.toInt()
            // add the new reward points and the leftover points
            if (pointsEarnedFromOrder != null) {
                newPointsTotal += pointsEarnedFromOrder
            }
            // push them to the database?????
            _newPointsFromOrder.value = newPointsTotal
            return newOrderTotal
        }
    }

}

