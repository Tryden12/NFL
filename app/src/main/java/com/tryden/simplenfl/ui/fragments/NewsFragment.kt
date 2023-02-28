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
import com.tryden.simplenfl.ui.epoxy.controllers.news.NewsEpoxyController
import com.tryden.simplenfl.ui.viewmodels.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val viewModel: NewsViewModel by viewModels()
    private val epoxyController = NewsEpoxyController(::onArticleSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyNewsRecyclerView = binding.epoxyNewsRecyclerView
        epoxyNewsRecyclerView.setController(epoxyController)
        epoxyController.setData(emptyList())

        viewModel.refreshHeadlines("", "30")
        viewModel.headlinesLiveData.observe(viewLifecycleOwner) { epoxyItems ->
            epoxyController.setData(epoxyItems)
        }

    }

    private fun onArticleSelected(articleId: String) {
        Log.e("NewsFragment", "onArticleSelected: $articleId" )

        val directions = NewsFragmentDirections.actionNewsFragmentToArticleFragment(articleId)
        findNavController().navigate(directions)
    }
}