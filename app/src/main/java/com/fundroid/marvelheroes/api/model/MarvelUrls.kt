package com.fundroid.marvelheroes.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelUrls(
    val type: String,
    val url: String
) : Parcelable
