package com.example.rickandmorty.ui.detail

import com.example.rickandmorty.domain.model.Character

data class DetailState(
    val character: Character? = null,
    val isLoading: Boolean = false
)