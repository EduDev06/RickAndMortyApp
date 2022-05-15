package com.example.rickandmorty.domain.use_case

import com.example.rickandmorty.data.Result
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.getCharacter(id)
    }
}