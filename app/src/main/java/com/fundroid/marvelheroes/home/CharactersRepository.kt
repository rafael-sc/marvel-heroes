package com.fundroid.marvelheroes.home

import com.fundroid.marvelheroes.api.MarvelAPI
import com.fundroid.marvelheroes.commom.Utils
import com.fundroid.marvelheroes.data.CharacterResponseResult
import java.lang.Exception
import java.util.*

class CharactersRepository(
    private val marvelAPI: MarvelAPI
) {

    suspend fun getCharacters(): CharacterResponseResult {
        return try {
            val result = marvelAPI.getMarvelCharactersAsync().await()
            val resultBody = result.body()
            if (result.isSuccessful && resultBody != null) {
                CharacterResponseResult.Success(resultBody.data.results)
            } else {
                CharacterResponseResult.Error("error")
            }

        } catch (ex: Exception) {
            CharacterResponseResult.Error("error")
        }
    }

}