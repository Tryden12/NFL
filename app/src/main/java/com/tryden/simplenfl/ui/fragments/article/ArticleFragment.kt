package com.tryden.simplenfl.ui.fragments.article

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.article.ArticleEpoxyController
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.collection


class ArticleFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val epoxyControllerArticle = ArticleEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        epoxySetup()
        topToolbarSetup()
    }

    private fun epoxySetup() {
        val epoxyArticleRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxy_article_RecyclerView)
        sharedViewModel.onArticleSelectedLiveData.observe(viewLifecycleOwner) { articleId ->
            Log.e("ArticleFragment", "onArticleSelected: $articleId" )

            sharedViewModel.refreshArticle(articleId)
        }
        sharedViewModel.articleByIdLiveDataResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                epoxyControllerArticle.articleResponse = response
                Log.e("ArticleFragment", "onViewCreated: ${response!!.headlines[0].headline}")
            }
        }
        epoxyArticleRecyclerView?.setControllerAndBuildModels(epoxyControllerArticle)
    }

    private fun topToolbarSetup() {
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.topMenuMaterialToolbar)
        toolbar?.setNavigationOnClickListener {
            (activity as AppCompatActivity?)!!.onBackPressed()
        }
        toolbar?.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.shareArticleTopBarMenuItem -> {
                    // Share the url to the article
                    shareArticle()
                    true
                }
                else -> false
            }
        }
    }

    private fun shareArticle() {
        val articleUrl = "https://a.espncdn.com/photo/2023/0126/r1122544_608x342_16-9.jpg"
        val uri = Uri.parse(articleUrl)
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.type = "image/*"
        sendIntent.clipData = ClipData.newRawUri("", uri)
        sendIntent.putExtra(Intent.EXTRA_TITLE, "title")

        sendIntent.putExtra(Intent.EXTRA_TEXT,"http://now.core.api.espn.com/v1/sports/news/35539696" )

        startActivity(Intent.createChooser(sendIntent, null))
    }
}