package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.domain.usecase.news.articleById.ArticleByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleByIdUseCase: ArticleByIdUseCase
) : ViewModel() {


    private val _articleById = MutableLiveData<Article>()
    val articleByIdLiveData: LiveData<Article> = _articleById

    fun refreshArticle(id: String) {
        viewModelScope.launch {
            val article = articleByIdUseCase.getArticleById(id)
            _articleById.postValue(article)
        }
    }
}