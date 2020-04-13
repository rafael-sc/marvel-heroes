package com.fundroid.marvelheroes.home

import com.fundroid.marvelheroes.api.MarvelAPI
import com.fundroid.marvelheroes.data.CharacterDetailResponseResult
import com.fundroid.marvelheroes.data.CharacterListResponseResult
import com.fundroid.marvelheroes.data.ComicsResponseResult

class CharactersRepository(
    private val marvelAPI: MarvelAPI
) {

    suspend fun getCharacters(currentPage: Int): CharacterListResponseResult {
        return try {

            var offset = 0
            if (currentPage > 1)
                offset = 10 * currentPage

            val result = marvelAPI.getMarvelCharactersAsync(offset = offset).await()
            val resultBody = result.body()
            if (result.isSuccessful && resultBody != null) {
                CharacterListResponseResult.Success(resultBody.data.results)
            } else {
                CharacterListResponseResult.Error("error: " + result.errorBody())
            }
        } catch (ex: Exception) {
            CharacterListResponseResult.Error("error: " + ex.message)
        }
    }

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailResponseResult {
        return try {
            val result = marvelAPI.getMarvelCharacterDetailAsync(characterId).await()
            val resultBody = result.body()
            if (result.isSuccessful && resultBody != null) {
                CharacterDetailResponseResult.Success(resultBody.data.results[0])
            } else {
                CharacterDetailResponseResult.Error("error: " + result.errorBody())
            }
        } catch (ex: Exception) {
            CharacterDetailResponseResult.Error("error: " + ex.message)
        }
    }

    suspend fun getCharacterComics(characterId: Int): ComicsResponseResult {
        return try {
            val result = marvelAPI.getMarvelCharacterComicsAsync(characterId).await()
            val resultBody = result.body()
            if (result.isSuccessful && resultBody != null) {
                ComicsResponseResult.Success(resultBody.data.results)
            } else {
                ComicsResponseResult.Error("error: " + result.errorBody())
            }
        } catch (ex: Exception) {
            ComicsResponseResult.Error("error: " + ex.message)
        }
    }
}
