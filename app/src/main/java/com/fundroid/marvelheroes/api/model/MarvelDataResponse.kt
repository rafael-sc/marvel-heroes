package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelDataResponse(
    val results: List<MarvelCharacter>
) : Parcelable