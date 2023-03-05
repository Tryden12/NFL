package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.FragmentHomeBinding
import com.tryden.simplenfl.ui.epoxy.controllers.home.HomeEpoxyController
import com.tryden.simplenfl.ui.viewmodels.FavoritesViewModel
import com.tryden.simplenfl.ui.viewmodels.NewsViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    private val newsViewModel by viewModels<NewsViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()

    private val epoxyController = HomeEpoxyController(::onArticleSelected)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)


        newsViewModel.refreshHeadlines("", "30")
        newsViewModel.headlinesLiveData.observe(viewLifecycleOwner) { epoxyItems ->
            epoxyController.headlineEpoxyItems = epoxyItems
        }

        favoritesViewModel.allFavoriteTeamsFlow.asLiveData().observe(viewLifecycleOwner) { favoritesList ->
            favoritesList.forEach {
                Log.e("HomeFragment", "favorites: ${it.shortName}", )
            }
            favoritesViewModel.refreshHeadlinesByTeamId(favoritesList, "30")
        }
        favoritesViewModel.newsFromFavorites.observe(viewLifecycleOwner) { epoxyItems ->
            Log.e("HomeFragment", "favorites epoxy items: ${epoxyItems.size}", )
            epoxyController.favoriteHeadlinesEpoxyItems = epoxyItems
        }
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("HomeFragment", "onArticleSelected: $articleId" )

        val directions = HomeFragmentDirections.actionHomeFragmentToArticleFragment(articleId)
        findNavController().navigate(directions)
    }


}