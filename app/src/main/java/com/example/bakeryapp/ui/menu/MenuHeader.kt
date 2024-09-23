package com.example.bakeryapp.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakeryapp.R
import com.example.bakeryapp.data.MenuItem
import com.example.bakeryapp.ui.theme.BakeryAppTheme
import com.example.bakeryapp.ui.theme.displayFontFamily
import com.example.bakeryapp.ui.theme.onPrimaryLight
import com.example.bakeryapp.ui.theme.primaryContainerLight
import com.example.bakeryapp.ui.theme.primaryLight

@Composable
fun MenuHeader (title : String) {
    Box(
        modifier = Modifier
            .background(onPrimaryLight)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = primaryLight,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMenuHeader(
) {
    BakeryAppTheme {
        MenuHeader(title = "Menu")
    }
}

// is this the best way to hold toe composables? since they are so small, maybe one file to hold them all?