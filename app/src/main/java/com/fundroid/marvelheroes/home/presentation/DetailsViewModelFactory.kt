package com.fundroid.marvelheroes.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fundroid.marvelheroes.api.MarvelAPI
import com.fundroid.marvelheroes.home.CharactersRepository
import com.fundroid.marvelheroes.home.domain.CharacterUseCase

class DetailsViewModelFactory : ViewModelProvider.Factory {

    private val api = MarvelAPI.getApi()
    private val charactersRepository = CharactersRepository(api)

    private val characterUseCase = CharacterUseCase(
        charactersRepository
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(characterUseCase) as T
    }
}
