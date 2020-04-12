package com.fundroid.marvelheroes.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.extension.observe
import com.fundroid.marvelheroes.home.presentation.DetailsViewModel
import com.fundroid.marvelheroes.home.presentation.DetailsViewModelFactory
import com.fundroid.marvelheroes.home.presentation.HomeViewModel
import com.fundroid.marvelheroes.home.presentation.HomeViewModelFactory
import com.fundroid.marvelheroes.home.ui.adapter.CharacterViewHolder

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
        return inflater.inflate(R.layout.bottomsheet_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCharacters(character!!.id)

        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.character) {
            character
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
