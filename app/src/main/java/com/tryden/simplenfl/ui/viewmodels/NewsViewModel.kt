package com.tryden.simplenfl.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.ui.epoxy.interfaces.news.HeadlinesEpoxyItem
import com.tryden.simplenfl.ui.repositories.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val repository = NewsRepository()

    private val _headlines = MutableLiveData<List<HeadlinesEpoxyItem>>()
    val headlinesLiveData: LiveData<List<HeadlinesEpoxyItem>> = _headlines

    fun refreshHeadlines(type: String, limit: String) {
        viewModelScope.launch {
            val headlines = repository.getHeadlines(type, limit)

            // create epoxy items list
            val epoxyItems = buildList {
                    add(HeadlinesEpoxyItem.HeaderItem(headerTitle = "Top Headlines"))
                    headlines!!.forEach {
                        add(HeadlinesEpoxyItem.HeadlineItem(headline = it))
                    }
                    add(HeadlinesEpoxyItem.FooterItem)
            }

            _headlines.postValue(epoxyItems)
        }
    }
}