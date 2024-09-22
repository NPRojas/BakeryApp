package com.example.bakeryapp

import com.example.bakeryapp.ui.MenuViewModel
import org.junit.Test

import org.junit.Assert.*

class MenuViewModelTest {
   private val viewModel = MenuViewModel()

    @Test
    fun `vm returns menu items`() {
        //get the menu items
        val menuItems = viewModel.menuItems

        // checks the menu size
        assertEquals(9, menuItems.size)
    }

    @Test
    fun `getMenuItemById returns menu item`() {
        // get menu item by id
        val menuItem = viewModel.getMenuItemById(0)
        val menuItemPrice : Double = 2.99

        assertNotNull(menuItem)
        assertEquals("Iced Coffee", menuItem.name)
        assertEquals(menuItemPrice, menuItem.price, .01)
    }
}