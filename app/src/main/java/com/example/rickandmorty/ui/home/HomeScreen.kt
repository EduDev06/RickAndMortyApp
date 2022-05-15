package com.example.rickandmorty.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.Characters
import com.example.rickandmorty.ui.home.components.CharacterItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    onItemClicked: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar(
                showPrevious = state.showPrevious,
                showNext = state.showNext,
                onPreviousPressed = { viewModel.getCharacters(false) },
                onNextPressed = { viewModel.getCharacters(true) }
            )
        }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            onItemClicked = { onItemClicked(it) },
            isLoading = state.isLoading,
            characters = state.characters
        )
    }
}

@Composable
private fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    isLoading: Boolean = false,
    characters: List<Characters> = emptyList()
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 6.dp),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(characters.size) { index ->
                    CharacterItem(
                        modifier = Modifier.fillMaxWidth(),
                        item = characters[index],
                        onItemClicked = { onItemClicked(it) }
                    )
                }
            }
        )
        if (isLoading) FullScreenLoading()
    }
}

@Composable
private fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showPrevious,
                onClick = onPreviousPressed
            ) {
                Text(text = stringResource(id = R.string.previous_button))
            }
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showNext,
                onClick = onNextPressed
            ) {
                Text(text = stringResource(id = R.string.next_button))
            }
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}