package com.example.bakeryapp

import android.view.Menu
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.ui.MenuViewModel
import com.example.bakeryapp.ui.view.MenuScreen
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MenuScreenTest {
    @get:Rule
    val testRule = createComposeRule()

    private fun mockMenuViewModel(): MenuViewModel {
        val viewModel = mockk<MenuViewModel>(relaxed = true)

        val isLoggedIn = MutableStateFlow(true)
        val points = MutableStateFlow(100)
        val tempPoints = MutableStateFlow(50)

        // Why do the mutable state flows need to be mocked?
        // VM is accessing them behind the scenes when observing state. When these values are
        // mocked with "every", they are simulate the behavior of these vals for the test to avoid missing values
        every { viewModel.isLoggedIn } returns isLoggedIn
        every { viewModel.points } returns points
        every { viewModel.tempPoints } returns tempPoints

        return viewModel
    }

    @Test
    fun menu_screen_loads() {
        testRule.setContent {
            MenuScreen(navController = rememberNavController(), viewModel = mockMenuViewModel())
        }
        // check if title shows
        testRule.onNodeWithText("Menu").assertExists()
    }
}