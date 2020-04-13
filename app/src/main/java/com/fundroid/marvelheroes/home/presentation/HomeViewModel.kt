package com.fundroid.marvelheroes.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.BaseViewModel
import com.fundroid.marvelheroes.commom.liveevent.LiveEvent
import com.fundroid.marvelheroes.commom.liveevent.MutableLiveEvent
import com.fundroid.marvelheroes.data.CharacterListResponseResult
import com.fundroid.marvelheroes.home.domain.CharacterUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    private var currentPage = 0

    private val _characters = MutableLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>>
        get() = _characters

    private val _noResultFound = MutableLiveEvent()
    val noResultFound: LiveEvent
        get() = _noResultFound

    fun getCharacters() {

        currentPage++
        viewModelScope.launch {
            when (val result = characterUseCase.getCharactersList(currentPage)) {
                is CharacterListResponseResult.Success -> _characters.postValue(result.characterList)
                is CharacterListResponseResult.Error -> _noResultFound.emit()
            }
        }
    }
}
