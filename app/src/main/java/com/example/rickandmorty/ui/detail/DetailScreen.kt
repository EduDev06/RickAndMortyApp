package com.example.rickandmorty.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.SafetyDivider
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.ui.detail.components.CharacterImage
import com.example.rickandmorty.ui.detail.components.DetailProperty
import com.example.rickandmorty.ui.detail.components.mirroringBackIcon

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    val state = viewModel.state
    DetailContent(
        character = state.character,
        upPress = upPress
    )
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    character: Character?,
    upPress: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Column {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                character = character
            )
            Body(character = character)
        }
        Up(upPress)
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    character: Character?
) {
    Column(
        modifier = modifier.background(Color(0xffffe0b2)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(image = character?.image)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = character?.name ?: "",
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
    }
}

@Composable
private fun Body(character: Character?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DetailProperty(label = stringResource(R.string.specie), value = character?.species, imageVector = Icons.Filled.EmojiPeople)
        DetailProperty(label = stringResource(R.string.status), value = character?.status, imageVector = Icons.Outlined.Help)
        DetailProperty(label = stringResource(R.string.gender), value = character?.gender, imageVector = Icons.Outlined.SafetyDivider)
        DetailProperty(label = stringResource(R.string.first_location), value = character?.origin?.name, imageVector = Icons.Outlined.Visibility)
        DetailProperty(label = stringResource(R.string.last_location), value = character?.location?.name, imageVector = Icons.Outlined.LocationOn)
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .size(36.dp)
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = Color(0xffffffff),
            contentDescription = null
        )
    }
}