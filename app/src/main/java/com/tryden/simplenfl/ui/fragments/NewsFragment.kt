package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentNewsBinding
import com.tryden.simplenfl.epoxy.controllers.news.NewsTopHeadlinesEpoxyController

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val epoxyControllerTopHeadlines = NewsTopHeadlinesEpoxyController(::onArticleSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyNewsTopHeadlinesRecyclerView= binding.epoxyNewsTopHeadlinesRecyclerView

        sharedViewModel.newsBreakingLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTopHeadlines.newsResponse = response
            epoxyControllerTopHeadlines.maxHeadlines = 8
        }
        sharedViewModel.refreshBreakingNews("","100")

        epoxyNewsTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("NewsFragment", "onArticleSelected: $articleId" )

        sharedViewModel.saveCurrentArticleId(articleId = articleId)
        findNavController().navigate(R.id.action_newsFragment_to_articleFragment)
    }
}