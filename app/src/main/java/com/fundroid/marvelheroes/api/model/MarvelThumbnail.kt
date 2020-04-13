package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelThumbnail(
    val path: String,
    val extension: String
) : Parcelable
