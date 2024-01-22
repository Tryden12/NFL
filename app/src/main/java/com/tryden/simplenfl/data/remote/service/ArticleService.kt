package com.tryden.simplenfl.data.remote.service

import com.tryden.simplenfl.data.remote.dto.ArticleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("{article-id}")
    suspend fun getArticleById(
        @Path("article-id") articleId: String
    ) : Response<ArticleDto>
}