package com.fundroid.marvelheroes.home.domain

import com.fundroid.marvelheroes.data.CharacterResponseResult
import com.fundroid.marvelheroes.home.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CharacterUseCase(
    private val charactersRepository: CharactersRepository
) {

    lateinit var characters: CharacterResponseResult
    suspend fun getCharacters(): CharacterResponseResult {

        coroutineScope {
            launch {
                characters = charactersRepository.getCharacters()
            }
        }

        return characters

    }
}