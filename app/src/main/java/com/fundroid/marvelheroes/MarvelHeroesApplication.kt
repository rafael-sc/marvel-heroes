package com.fundroid.marvelheroes

import android.app.Application
import com.fundroid.marvelheroes.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class MarvelHeroesApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        startKoin {
            androidContext(this@MarvelHeroesApplication)
            modules(listOf(homeViewModelModule))
        }
    }

}