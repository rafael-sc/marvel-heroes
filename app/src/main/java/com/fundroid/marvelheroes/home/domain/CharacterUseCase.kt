package com.fundroid.marvelheroes.home.domain

import com.fundroid.marvelheroes.data.CharacterDetailResponseResult
import com.fundroid.marvelheroes.data.CharacterListResponseResult
import com.fundroid.marvelheroes.home.CharactersRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CharacterUseCase(
    private val charactersRepository: CharactersRepository
) {
    lateinit var characters: CharacterListResponseResult
    lateinit var character: CharacterDetailResponseResult

    suspend fun getCharactersList(): CharacterListResponseResult {
        coroutineScope {
            launch {
                characters = charactersRepository.getCharacters()
            }
        }
        return characters
    }

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailResponseResult {

        coroutineScope {
            launch {
                character = charactersRepository.getCharacterdetail(characterId)
            }
        }
        return character
    }
}