package com.fundroid.marvelheroes.commom.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fundroid.marvelheroes.R

fun ImageView.loadUrl(url: String) {
    val requestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.ic_marvel_logo)

    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}