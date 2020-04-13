package com.fundroid.marvelheroes.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.ImageUrlBuilder
import com.fundroid.marvelheroes.commom.extension.loadUrl
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
) {

    fun bindViewHolder(character: MarvelCharacter) {
        itemView.titleTextView.text = character.name

        val imageUrl = ImageUrlBuilder().buildCharacterUrl(character.thumbnail)
        itemView.imageViewCharacter.loadUrl(imageUrl)
    }
}
