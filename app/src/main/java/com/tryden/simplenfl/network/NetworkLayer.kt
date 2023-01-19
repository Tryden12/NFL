package com.tryden.simplenfl.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.simplenfl.network.service.ArticleByIDService
import com.tryden.simplenfl.network.service.NFLService
import com.tryden.simplenfl.network.service.PlayerByIdService
import com.tryden.simplenfl.util.Constants.ARTICLE_BY_ID_URL
import com.tryden.simplenfl.util.Constants.BASE_URL_NFL
import com.tryden.simplenfl.util.Constants.PLAYER_BY_ID_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    // Converter factory
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    /**
     * Retrofit
     */
    // Common NFL endpoints
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_NFL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Article by Id endpoint (different base URL)
    val retrofitArticle = Retrofit.Builder()
        .baseUrl(ARTICLE_BY_ID_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofitPlayer = Retrofit.Builder()
        .baseUrl(PLAYER_BY_ID_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    /**
     * Services
     */
    // Common NFL endpoints
    val nflService: NFLService = retrofit.create(NFLService::class.java)
    // Article by id only
    val articleByIDService: ArticleByIDService = retrofitArticle.create(ArticleByIDService::class.java)
    // Player by id only
    val playerByIdService: PlayerByIdService = retrofitPlayer.create(PlayerByIdService::class.java)


    /**
     * Client
     */
    val apiClient = ApiClient(nflService, articleByIDService, playerByIdService)

}