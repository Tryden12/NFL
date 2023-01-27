package com.tryden.simplenfl.ui.fragments.article

import android.os.Bundle
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
            textView.text = response!!.headlines[0].title
        }
        sharedViewModel.onArticleSelectedLiveData.observe(viewLifecycleOwner) { articleId ->
            Log.e("ArticleFragment", "onArticleSelected: $articleId" )

            sharedViewModel.refreshArticle(articleId)
        }

    }

}