package com.fundroid.marvelheroes.home.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fundroid.marvelheroes.api.model.MarvelCharacter

class HomeAdapter(
    private val characterList: MutableList<MarvelCharacter>,
    private val onClickListener: ItemClickListener
) : RecyclerView.Adapter<CharacterViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(character: MarvelCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(parent)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bindViewHolder(character)
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(character)
        }
    }

    fun insertItems(characters: List<MarvelCharacter>) {
        val startPosition = itemCount - 1
        characterList.addAll(characters.distinct())
        notifyItemRangeInserted(startPosition, characters.size)
    }
}
