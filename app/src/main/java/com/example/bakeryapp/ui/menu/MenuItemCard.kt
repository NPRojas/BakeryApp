package com.example.bakeryapp.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bakeryapp.R
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.ui.theme.BakeryAppTheme

@Composable
fun MenuItemCard (item: MenuItem) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(id = item.img),
                contentDescription = item.name
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier
                    .weight(1f), horizontalAlignment = Alignment.Start
            ) {
                Text(text = item.name)
                Text(text = "$${item.price}")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMenuItemCard(
) {
    BakeryAppTheme {
      MenuItemCard(item = MenuItem(name = "Croissant", price = 10.0, img = R.drawable.croissant))
    }
}