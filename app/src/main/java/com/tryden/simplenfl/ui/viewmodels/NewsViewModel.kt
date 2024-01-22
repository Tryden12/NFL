package com.tryden.simplenfl.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.models.news.Headline
import com.tryden.simplenfl.domain.usecase.news.allNews.AllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for news headlines.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val allNewsUseCase: AllNewsUseCase
): ViewModel() {

    private val _headlines = MutableLiveData<List<Headline>>()
    val headlinesLiveData: LiveData<List<Headline>> = _headlines

    fun refreshHeadlines(type: String, limit: String) {
        viewModelScope.launch {
            val headlines = allNewsUseCase.getNews(type, limit)
            if (!headlines.isNullOrEmpty()) {
                _headlines.postValue(headlines!!)
            } else {
                Log.d("NewsViewModel()", "news list size = ${headlines?.size}")
            }
        }
    }
}