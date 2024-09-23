package com.example.bakeryapp.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bakeryapp.R
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.ui.theme.BakeryAppTheme
import com.example.bakeryapp.ui.theme.onPrimaryContainerLight
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.onSecondaryContainerLight
import com.example.bakeryapp.ui.theme.primaryContainerLight
import com.example.bakeryapp.ui.theme.primaryLight

@Composable
fun MenuItemCard (item: MenuItem, onClick:() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = primaryContainerLight),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onClick()}
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
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier
                    .weight(1f), horizontalAlignment = Alignment.Start
            ) {
                Text(text = item.name, style = MaterialTheme.typography.bodyMedium,  fontWeight = FontWeight.Bold, color = primaryLight)
                Text(text = "$${item.price}", style = MaterialTheme.typography.bodyMedium, color = primaryLight)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMenuItemCard(
) {
    BakeryAppTheme {
    }
}