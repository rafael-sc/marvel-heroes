package com.fundroid.marvelheroes.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.api.model.MarvelComics
import com.fundroid.marvelheroes.commom.BaseViewModel
import com.fundroid.marvelheroes.commom.liveevent.LiveEvent
import com.fundroid.marvelheroes.commom.liveevent.MutableLiveEvent
import com.fundroid.marvelheroes.data.CharacterDetailResponseResult
import com.fundroid.marvelheroes.data.ComicsResponseResult
import com.fundroid.marvelheroes.home.domain.CharacterUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    private val _character = MutableLiveData<MarvelCharacter>()
    val character: LiveData<MarvelCharacter>
        get() = _character

    private val _comics = MutableLiveData<List<MarvelComics>>()
    val comics: LiveData<List<MarvelComics>>
        get() = _comics

    private val _noResultFound = MutableLiveEvent()
    val noResultFound: LiveEvent
        get() = _noResultFound

    private val _noComicFound = MutableLiveEvent()
    val noComicFound: LiveEvent
        get() = _noComicFound

    fun getCharacterInfo(characterId: Int) {
        getCharacters(characterId)
        getComics(characterId)
    }

    private fun getCharacters(characterId: Int) {
        viewModelScope.launch {
            when (val result = characterUseCase.getCharacterDetail(characterId)) {
                is CharacterDetailResponseResult.Success -> _character.postValue(result.character)
                is CharacterDetailResponseResult.Error -> _noResultFound.emit()
            }
        }
    }

    private fun getComics(characterId: Int) {
        viewModelScope.launch {
            when (val result = characterUseCase.getCharacterComics(characterId)) {
                is ComicsResponseResult.Success -> {
                    if (result.comics.isEmpty())
                        _noComicFound.emit()
                    else
                        _comics.postValue(result.comics)
                }
                is ComicsResponseResult.Error -> _noComicFound.emit()
            }
        }
    }
}
