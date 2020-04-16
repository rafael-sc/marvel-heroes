package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelAPIComicsResponse(
    val code: Int,
    val status: String,
    val data: MarvelComicDataResponse
) : Parcelable
