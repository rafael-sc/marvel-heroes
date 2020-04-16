package com.fundroid.marvelheroes.di

import com.fundroid.marvelheroes.api.MarvelAPI
import com.fundroid.marvelheroes.home.CharactersRepository
import com.fundroid.marvelheroes.home.domain.CharacterUseCase
import com.fundroid.marvelheroes.home.presentation.DetailsViewModel
import com.fundroid.marvelheroes.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module =
    module {
        single { MarvelAPI.getApi() }
        factory { CharactersRepository(get()) }
        factory { CharacterUseCase(get()) }
    }
val homeViewModelModule: Module =
    module {
        single { HomeViewModel(get()) }
    }

val detailsViewModelModule: Module =
    module {
        single { DetailsViewModel(get()) }
    }
