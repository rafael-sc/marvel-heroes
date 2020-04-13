package com.fundroid.marvelheroes.home

import com.fundroid.marvelheroes.api.MarvelAPI
import com.fundroid.marvelheroes.data.CharacterDetailResponseResult
import com.fundroid.marvelheroes.data.CharacterListResponseResult
import java.lang.Exception

class CharactersRepository(
    private val marvelAPI: MarvelAPI
) {

    suspend fun getCharacters(currentPage: Int): CharacterListResponseResult {
        return try {

            var offset = 0
            if (currentPage > 1)
                offset = 10

            val result = marvelAPI.getMarvelCharactersAsync(offset = currentPage * offset).await()
            val resultBody = result.body()
            if (result.isSuccessful && resultBody != null) {
                CharacterListResponseResult.Success(resultBody.data.results)
            } else {
                CharacterListResponseResult.Error("error")
            }

        } catch (ex: Exception) {
            CharacterListResponseResult.Error("error")
        }
    }

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailResponseResult {
        return try {
            val result = marvelAPI.getMarvelCharacterDetailAsync(characterId).await()
            val resultBody = result.body()
            if (result.isSuccessful && resultBody != null) {
                CharacterDetailResponseResult.Success(resultBody.data.results[0])
            } else {
                CharacterDetailResponseResult.Error("error")
            }

        } catch (ex: Exception) {
            CharacterDetailResponseResult.Error("error")
        }
    }
}
