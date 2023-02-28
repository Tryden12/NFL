package com.tryden.simplenfl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tryden.simplenfl.domain.models.article.Article
import com.tryden.simplenfl.ui.repositories.ArticleRepository
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    private val repository = ArticleRepository()

    private val _articleById = MutableLiveData<Article?>()
    val articleByIdLiveData: LiveData<Article?> = _articleById

    fun refreshArticle(articleId: String) {
        viewModelScope.launch {
            val article = repository.getArticleById(articleId)

            _articleById.postValue(article)
        }
    }
}