package com.fundroid.marvelheroes.data

import com.fundroid.marvelheroes.api.model.MarvelAPIResponse

sealed class CharacterResponseResult {
    data class Success(val characterList: MarvelAPIResponse?) : CharacterResponseResult()
    data class Error(val message: String) : CharacterResponseResult()
}