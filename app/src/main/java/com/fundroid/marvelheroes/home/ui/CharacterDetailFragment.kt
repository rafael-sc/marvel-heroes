package com.fundroid.marvelheroes.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.api.model.MarvelThumbnail
import com.fundroid.marvelheroes.commom.ImageUrlBuilder
import com.fundroid.marvelheroes.commom.extension.loadUrl
import com.fundroid.marvelheroes.commom.extension.observe
import com.fundroid.marvelheroes.home.presentation.DetailsViewModel
import com.fundroid.marvelheroes.home.presentation.DetailsViewModelFactory
import kotlinx.android.synthetic.main.character_details_fragment.*
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterDetailFragment : Fragment() {
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(requireActivity(), DetailsViewModelFactory())
            .get(DetailsViewModel::class.java)
    }


    val character: MarvelCharacter? by lazy {
        arguments?.getParcelable<MarvelCharacter>(ARG_MARVEL_CHARACTER)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCharacters(character!!.id)

        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.character) {
            character?.let {
                textViewDescription.text = it.description
                textViewName.text = it.name

                imageViewCharacter.loadUrl(ImageUrlBuilder().buildCharacterUrl(it.thumbnail))
            }
        }
    }

    companion object {
        const val ARG_MARVEL_CHARACTER = "item_count"

        fun newInstance(marvelCharacter: MarvelCharacter): CharacterDetailFragment =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MARVEL_CHARACTER, marvelCharacter)
                }
            }

    }
}
