package com.fundroid.marvelheroes.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.ImageUrlBuilder
import kotlinx.android.synthetic.main.character_item.view.*


class CharacterViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
) {

    fun bindViewHolder(character: MarvelCharacter) {
        itemView.titleTextView.text = character.name

        val imageUrl = ImageUrlBuilder().buildCharacterUrl(character.thumbnail)

        val requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_marvel_logo)

        Glide.with(itemView)
            .load(imageUrl)
            .apply(requestOptions)
            .into(itemView.ImageViewCharacter)
    }
}