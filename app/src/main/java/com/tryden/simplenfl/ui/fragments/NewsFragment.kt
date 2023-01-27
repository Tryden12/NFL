package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.news.NewsTopHeadlinesEpoxyController
import com.tryden.simplenfl.epoxy.controllers.news.home.topheadlines.HomeTopHeadlinesEpoxyController

class NewsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val epoxyControllerTopHeadlines = NewsTopHeadlinesEpoxyController()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyNewsTopHeadlinesRecyclerView= view.findViewById<EpoxyRecyclerView>(R.id.epoxy_news_top_headlines_RecyclerView)

        sharedViewModel.newsBreakingLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTopHeadlines.newsResponse = response
            epoxyControllerTopHeadlines.maxHeadlines = 8
        }
        sharedViewModel.refreshBreakingNews("","100")

        epoxyNewsTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)

    }
}