package com.fundroid.marvelheroes.data

import com.fundroid.marvelheroes.api.model.MarvelCharacter

sealed class CharacterListResponseResult {
    data class Success(val characterList: List<MarvelCharacter>) : CharacterListResponseResult()
    data class Error(val message: String) : CharacterListResponseResult()
}

sealed class CharacterDetailResponseResult {
    data class Success(val character: MarvelCharacter) : CharacterDetailResponseResult()
    data class Error(val message: String) : CharacterDetailResponseResult()
}