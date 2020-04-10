package com.fundroid.marvelheroes.api.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: MarvelImage
) : Parcelable

