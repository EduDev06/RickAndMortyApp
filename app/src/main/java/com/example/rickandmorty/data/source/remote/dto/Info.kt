package com.example.rickandmorty.data.source.remote.dto

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)