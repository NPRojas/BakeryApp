package com.example.bakeryapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bakeryapp.data.MenuRepository
import com.example.bakeryapp.ui.MenuViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
// i tried using mock first and the unit test here because issues with accessing context
class MenuViewModelTest {
   private lateinit var repository: MenuRepository
   private lateinit var viewModel: MenuViewModel

   @Before
   fun setUp() {
       val context = ApplicationProvider.getApplicationContext<Context>()
       repository = MenuRepository(context)
       viewModel = MenuViewModel(repository)
   }

    @Test
    fun vm_returns_menu_items() {
        //get the menu items
        val menuItems = viewModel.menuItems

        // checks the menu size
        assertEquals(9, menuItems.size)
    }

    @Test
    fun getMenuItemById_returns_menu_item() {
        // get menu item by id
        val menuItem = viewModel.getMenuItemById(0)
        val menuItemPrice : Double = 2.99

        assertNotNull(menuItem)
        assertEquals("Iced Coffee", menuItem.name)
        assertEquals(menuItemPrice, menuItem.price, .01)
    }
}