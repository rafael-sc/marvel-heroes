package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelStories(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelItems>,
    val returned: Int
) : Parcelable