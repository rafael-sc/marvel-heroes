package com.fundroid.marvelheroes.di

import com.fundroid.marvelheroes.api.MarvelAPI
import com.fundroid.marvelheroes.home.CharactersRepository
import com.fundroid.marvelheroes.home.domain.CharacterUseCase
import org.koin.dsl.module

val homeViewModelModule =
    module {
        single { MarvelAPI }
        factory { CharactersRepository(get()) }
        factory { CharacterUseCase(get()) }
    }
