package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelComics(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelItems>,
    val returned: Int,
    val title: String,
    val thumbnail: MarvelThumbnail
) : Parcelable
