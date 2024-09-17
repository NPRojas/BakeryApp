package com.example.bakeryapp.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bakeryapp.R
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.data.MenuViewModel
import com.example.bakeryapp.ui.theme.BakeryAppTheme


@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    onNavItemClick: (String) -> Unit) {

    // retrieve the menu items from vm
    val menuItems = viewModel.menuItems
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        MenuHeader(title = "Menu")
        
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(menuItems) {menuItem ->
                MenuItemCard(menuItem)}
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {
   val viewModel = MenuViewModel()

    // Pass a no-op lambda for onNavItemClick
    MenuScreen(viewModel = viewModel, onNavItemClick = { _ -> })
}
