package com.example.rickandmorty.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.rickandmorty.domain.model.Characters

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    item: Characters,
    onItemClicked: (Int) -> Unit
) {

    Row(
        modifier = modifier
            .clickable { onItemClicked(item.id) }
            .padding(start = 6.dp, top = 12.dp, bottom = 12.dp)
    ) {
        CharacterImageContainer(modifier = Modifier.size(64.dp)) {
            CharacterImage(item)
        }
        Spacer(Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = item.specie,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Divider(modifier = Modifier.padding(top = 10.dp))
    }
}

// This function may be private
@Composable
fun CharacterImage(item: Characters) {
    Box {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
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

// This function may be private
@Composable
fun CharacterImageContainer(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), RoundedCornerShape(4.dp)) {
       content()
    }
}