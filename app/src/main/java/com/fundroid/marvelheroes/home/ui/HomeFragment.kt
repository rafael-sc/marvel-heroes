package com.fundroid.marvelheroes.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fundroid.marvelheroes.R
import com.fundroid.marvelheroes.api.model.MarvelCharacter
import com.fundroid.marvelheroes.commom.extension.observe
import com.fundroid.marvelheroes.home.presentation.HomeViewModel
import com.fundroid.marvelheroes.home.presentation.HomeViewModelFactory
import com.fundroid.marvelheroes.home.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(requireActivity(), HomeViewModelFactory())
            .get(HomeViewModel::class.java)
    }

    private var homeAdapter: HomeAdapter? = null
    lateinit var linearLayoutManager: LinearLayoutManager
    private var targetFragment = this

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
        viewModel.getCharacters()
        observe(viewModel.characters) {
            updateCharactersList(it)
        }
    }

    private fun updateCharactersList(results: List<MarvelCharacter>) {
        if (homeAdapter == null)
            createAdapter(results.toMutableList())
        else
            updateAdapter(results.toMutableList())
    }

    private fun updateAdapter(results: List<MarvelCharacter>) {
        homeAdapter?.insertItems(results)
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
        recyclerViewCharacters.adapter = homeAdapter
        progressBar.visibility = View.GONE
    }
}