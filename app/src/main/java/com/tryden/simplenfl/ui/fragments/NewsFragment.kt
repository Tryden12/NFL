package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.databinding.FragmentNewsBinding
import com.tryden.simplenfl.ui.epoxy.EpoxyDataManager
import com.tryden.simplenfl.ui.epoxy.controllers.news.NewsEpoxyController
import com.tryden.simplenfl.ui.viewmodels.NewsViewModel
import com.tryden.simplenfl.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val viewModel: NewsViewModel by viewModels()
    private val epoxyController = NewsEpoxyController(::onArticleSelected)
    private val epoxyDataManager = EpoxyDataManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.epoxyNewsRecyclerView.setController(epoxyController)
        epoxyController.setData(emptyList())

        viewModel.refreshHeadlines(Constants.HEADLINE_NEWS, "50")
        viewModel.headlinesLiveData.observe(viewLifecycleOwner) { headlines ->
            epoxyController.setData(
                epoxyDataManager.giveMeNewsHeadlines(headlines)
            )
        }

    }

    private fun onArticleSelected(articleId: String) {
        Log.e("NewsFragment", "onArticleSelected: $articleId" )

        val directions = NewsFragmentDirections.actionNewsFragmentToArticleFragment(articleId)
        findNavController().navigate(directions)
    }
}