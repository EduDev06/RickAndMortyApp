package com.example.rickandmorty.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun CharacterImage(image: String?) {
    ImageContainer(modifier = Modifier.size(128.dp)) {
        Box {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .size(Size.ORIGINAL)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

// This function may be private
@Composable
private fun ImageContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), shape = CircleShape) {
        content()
    }
}