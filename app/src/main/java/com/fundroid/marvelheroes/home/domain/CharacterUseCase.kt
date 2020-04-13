package com.fundroid.marvelheroes.home.domain

import com.fundroid.marvelheroes.data.CharacterDetailResponseResult
import com.fundroid.marvelheroes.data.CharacterListResponseResult
import com.fundroid.marvelheroes.data.ComicsResponseResult
import com.fundroid.marvelheroes.home.CharactersRepository

class CharacterUseCase(
    private val charactersRepository: CharactersRepository
) {
    suspend fun getCharactersList(currentPage: Int): CharacterListResponseResult {
        return charactersRepository.getCharacters(currentPage)
    }
    suspend fun getCharacterDetail(characterId: Int): CharacterDetailResponseResult {
        return charactersRepository.getCharacterDetail(characterId)
    }

    suspend fun getCharacterComics(characterId: Int): ComicsResponseResult {
        return charactersRepository.getCharacterComics(characterId)
    }
}