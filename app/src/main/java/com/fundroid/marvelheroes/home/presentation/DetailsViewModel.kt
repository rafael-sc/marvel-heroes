package com.fundroid.marvelheroes.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.BaseViewModel
import com.fundroid.marvelheroes.commom.liveevent.LiveEvent
import com.fundroid.marvelheroes.commom.liveevent.MutableLiveEvent
import com.fundroid.marvelheroes.data.CharacterDetailResponseResult
import com.fundroid.marvelheroes.home.domain.CharacterUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    private val _character = MutableLiveData<MarvelCharacter>()
    val character: LiveData<MarvelCharacter>
        get() = _character

    private val _noResultFound = MutableLiveEvent()
    val noResultFound: LiveEvent
        get() = _noResultFound

    fun getCharacters(characterId: Int) {
        viewModelScope.launch {
            when (val result = characterUseCase.getCharacterDetail(characterId)) {
                is CharacterDetailResponseResult.Success -> _character.postValue(result.character)
                is CharacterDetailResponseResult.Error -> _noResultFound.emit()
            }
        }
    }
}