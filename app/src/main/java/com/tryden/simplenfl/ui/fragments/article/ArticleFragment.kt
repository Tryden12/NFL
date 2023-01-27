package com.tryden.simplenfl.ui.fragments.article

import kotlin.text.StringBuilder
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel


class ArticleFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.articleTextView)
        sharedViewModel.articleByIdLiveDataResponse.observe(viewLifecycleOwner) { response ->
            var html = response!!.headlines[0].story
            Log.e("ArticleFragment", "storyBefore: $html" )

            var htmlWithAddedBrTags = addBrTags(html = html)
            Log.e("ArticleFragment", "storyAfter: $htmlWithAddedBrTags" )

            textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(htmlWithAddedBrTags.toString(), Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(htmlWithAddedBrTags.toString())
            }

        }
        sharedViewModel.onArticleSelectedLiveData.observe(viewLifecycleOwner) { articleId ->
            Log.e("ArticleFragment", "onArticleSelected: $articleId" )

            sharedViewModel.refreshArticle(articleId)
        }

    }

    private fun addBrTags(html: String) : StringBuilder {
        var builder = StringBuilder()
        builder.append(html)
        for (i in builder.indices) {
            if(builder[i] == 'p' && builder[i-1] == '/') {
                builder.insert(i+2, "<br>")
            }
        }
        return builder
    }

}