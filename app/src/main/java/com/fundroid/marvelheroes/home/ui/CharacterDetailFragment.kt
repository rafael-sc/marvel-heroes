package com.fundroid.marvelheroes.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.api.model.MarvelComics
import com.fundroid.marvelheroes.commom.ImageUrlBuilder
import com.fundroid.marvelheroes.commom.extension.loadUrl
import com.fundroid.marvelheroes.commom.extension.observe
import com.fundroid.marvelheroes.commom.liveevent.consume
import com.fundroid.marvelheroes.home.presentation.DetailsViewModel
import com.fundroid.marvelheroes.home.presentation.DetailsViewModelFactory
import kotlinx.android.synthetic.main.character_details_fragment.*

class CharacterDetailFragment : Fragment() {

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(requireActivity(), DetailsViewModelFactory())
            .get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getCharacterInfo(arguments?.getParcelable<MarvelCharacter>(ARG_MARVEL_CHARACTER)!!.id)
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.character) {
            it.let {
                if (it.description != "")
                    textViewDescription.text = it.description
                else
                    textViewDescription.text = getString(R.string.description_not_available)


                textViewName.text = it.name
                imageViewCharacter.loadUrl(ImageUrlBuilder().buildCharacterUrl(it.thumbnail))
            }
        }

        consume(viewModel.noComicFound) {
            updateComicsSectionVisibility(View.GONE)
        }

        observe(viewModel.comics) {
            if (it.isNotEmpty()) {
                loadComics(it)
            }
        }
    }

    private fun updateComicsSectionVisibility(visibility: Int) {
        textViewComicsTitle.visibility = visibility
        gridLayoutComics.visibility = visibility
    }

    private fun loadComics(comics: List<MarvelComics>) {
        if (comics.isNotEmpty()) {
            imageViewComic1.loadUrl(ImageUrlBuilder().buildCharacterUrl(comics[0].thumbnail))
            textViewComic1.text = (comics[0].title)
            linearLayoutComic1.visibility = View.VISIBLE
            updateComicsSectionVisibility(View.VISIBLE)
        }
        if (comics.size >= 2) {
            imageViewComic2.loadUrl(ImageUrlBuilder().buildCharacterUrl(comics[1].thumbnail))
            textViewComic2.text = (comics[1].title)
            linearLayoutComic2.visibility = View.VISIBLE
        }
        if (comics.size >= 3) {
            imageViewComic3.loadUrl(ImageUrlBuilder().buildCharacterUrl(comics[2].thumbnail))
            linearLayoutComic3.visibility = View.VISIBLE
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
