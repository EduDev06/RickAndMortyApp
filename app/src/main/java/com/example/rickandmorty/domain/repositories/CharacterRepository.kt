package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.data.Result
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.model.Characters
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(page: Int): Flow<Result<List<Characters>>>

    suspend fun getCharacter(id: Int): Result<Character>
}