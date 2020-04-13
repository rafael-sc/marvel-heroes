package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelAPIResponse(
    val code: Int,
    val status: String,
    val data: MarvelDataResponse
) : Parcelable
