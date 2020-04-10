package com.fundroid.marvelheroes.home.presentation

import android.util.Log
import com.fundroid.marvelheroes.commom.BaseViewModel
import com.fundroid.marvelheroes.data.CharacterResponseResult
import com.fundroid.marvelheroes.home.domain.CharacterUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    fun getCharacters() {
        viewModelScope.launch {
            when (val result = characterUseCase.getCharacters()) {
                is CharacterResponseResult.Success ->
                    result.characterList?.let {
                        it.data.results.forEach {
                            Log.d("getchar", it.name + it.description)
                        }
                    }

                is CharacterResponseResult.Error -> Log.d("getchar", "error")
            }
        }
    }


}