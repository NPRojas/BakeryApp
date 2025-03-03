package com.example.bakeryapp.ui.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

// to load the image from assets
@Composable
fun MenuItemImage(imageName: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bitmap = remember(imageName) {
        val assetManager = context.assets
        val inputStream = assetManager.open(imageName)
        BitmapFactory.decodeStream(inputStream)
    }

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
        modifier = modifier
    )
}