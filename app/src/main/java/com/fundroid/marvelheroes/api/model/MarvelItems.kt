package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelItems(
    val resourceURI: String,
    val name: String,
    val type: String
) : Parcelable