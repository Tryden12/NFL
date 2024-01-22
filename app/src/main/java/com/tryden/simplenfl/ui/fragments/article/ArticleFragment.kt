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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.FragmentArticleBinding
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.ui.epoxy.controllers.article.ArticleEpoxyController
import com.tryden.simplenfl.ui.viewmodels.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding

    private val viewModel: ArticleViewModel by viewModels()
    private val epoxyController = ArticleEpoxyController()

    private val safeArgs: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        epoxySetup()
        topToolbarSetup()
    }

    private fun epoxySetup() {
        // Set articleId
        val articleId = safeArgs.articleId
        viewModel.refreshArticle(articleId)
        Log.d("ArticleFragment", "onArticleSelected: $articleId" )

        val epoxyArticleRecyclerView = binding.epoxyArticleRecyclerView
        epoxyArticleRecyclerView.setController(epoxyController)
        epoxyController.setData(Article())
        viewModel.articleByIdLiveData.observe(viewLifecycleOwner) { article ->
            Log.d("ArticleFragment", "onViewCreated: ${article.headline}")
            epoxyController.setData(article)
        }
    }

    private fun topToolbarSetup() {
        val toolbar = binding.topMenuMaterialToolbar
        toolbar.setNavigationOnClickListener {
            (activity as AppCompatActivity?)!!.onBackPressed()
        }
        toolbar.setOnMenuItemClickListener { menuItem ->
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