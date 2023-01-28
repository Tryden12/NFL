package com.tryden.simplenfl.ui.fragments.article

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.article.ArticleEpoxyController


class ArticleFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val epoxyControllerArticle = ArticleEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        sharedViewModel.onArticleSelectedLiveData.observe(viewLifecycleOwner) { articleId ->
            Log.e("ArticleFragmenttt", "onArticleSelected: $articleId" )

            sharedViewModel.refreshArticle(articleId)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyArticleRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_article_RecyclerView)

        sharedViewModel.articleByIdLiveDataResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                epoxyControllerArticle.articleResponse = response
                Log.e("ArticleFragmentt", "onViewCreated: ${response!!.headlines[0].headline}", )

                // access article via web view
//                val mobileLink = response.headlines[0].links.mobile.href
//                webViewSetup(mobileLink)
            }
        }


        epoxyArticleRecyclerView.setControllerAndBuildModels(epoxyControllerArticle)

    }

//    private fun webViewSetup(url: String) {
//        val webView = view?.findViewById<WebView>(R.id.articleWebView)
//        webView?.webViewClient = WebViewClient()
//        webView?.apply {
//            loadUrl(url)
//            settings.javaScriptEnabled = true
//            settings.safeBrowsingEnabled = true
//
//        }
//    }

}