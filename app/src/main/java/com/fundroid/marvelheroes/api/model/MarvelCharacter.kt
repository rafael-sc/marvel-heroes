package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: MarvelThumbnail,
    val resourceURI: String,
    val comics: MarvelComics,
    val series: MarvelSeries,
    val stories: MarvelStories,
    val events: MarvelEvents,
    val urls: List<MarvelUrls>
) : Parcelable