package com.fundroid.marvelheroes.data

import com.fundroid.marvelheroes.api.model.MarvelCharacter

sealed class CharacterResponseResult {
    data class Success(val characterList: List<MarvelCharacter>) : CharacterResponseResult()
    data class Error(val message: String) : CharacterResponseResult()
}