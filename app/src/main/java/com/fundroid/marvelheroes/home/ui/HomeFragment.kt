package com.fundroid.marvelheroes.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.extension.observe
import com.fundroid.marvelheroes.home.presentation.HomeViewModel
import com.fundroid.marvelheroes.home.presentation.HomeViewModelFactory
import com.fundroid.marvelheroes.home.ui.adapter.HomeAdapter
import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(requireActivity(), HomeViewModelFactory())
            .get(HomeViewModel::class.java)
    }

    private var homeAdapter: HomeAdapter? = null
    private var endlessRecyclerViewAdapter: EndlessRecyclerViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.characters) {
            loadCharactersAdapter(it)
        }
        viewModel.getCharacters()
    }

    private fun loadCharactersAdapter(results: List<MarvelCharacter>) {
        if (homeAdapter == null)
            createAdapter(results.toMutableList())
        else
            updateAdapter(results)
    }

    private fun updateAdapter(results: List<MarvelCharacter>) {
        homeAdapter?.insertItems(results)
        endlessRecyclerViewAdapter?.onDataReady(true)
    }

    private fun createAdapter(results: MutableList<MarvelCharacter>) {
        homeAdapter = HomeAdapter(results, object : HomeAdapter.ItemClickListener {
            override fun onItemClick(character: MarvelCharacter) {
                fragmentManager?.run {
                    beginTransaction()
                        .add(R.id.container, CharacterDetailFragment.newInstance(character))
                        .addToBackStack("this")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
        })

        endlessRecyclerViewAdapter = EndlessRecyclerViewAdapter(homeAdapter) {
            viewModel.getCharacters()
        }

        recyclerViewCharacters.adapter = endlessRecyclerViewAdapter
        progressBar.visibility = View.GONE
    }
}