package com.tryden.simplenfl.data.remote

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.data.remote.service.ArticleByIDService
import com.tryden.simplenfl.data.remote.service.NFLService
import com.tryden.simplenfl.data.remote.service.PlayerByIdService
import com.tryden.simplenfl.util.Constants.ARTICLE_BY_ID_URL
import com.tryden.simplenfl.util.Constants.BASE_URL_NFL
import com.tryden.simplenfl.util.Constants.PLAYER_BY_ID_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkLayer {

    // Converter factory
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    /**
     * Retrofit
     */
    // Common NFL endpoints
    val retrofit = Retrofit.Builder()
        .client(getLoggingHttpClient())
        .baseUrl(BASE_URL_NFL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Article by Id endpoint (different base URL)
    val retrofitArticle = Retrofit.Builder()
        .baseUrl(ARTICLE_BY_ID_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Player by Id endpoint
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
     * Api Client
     */
    val apiClient = ApiClient(nflService, articleByIDService, playerByIdService)


    /**
     * OkHttp Client for Retrofit
     */
    private fun getLoggingHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder
            .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        builder
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        builder
            .addInterceptor(ChuckerInterceptor.Builder(SimpleNFLApplication.context)
            .collector(ChuckerCollector(SimpleNFLApplication.context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build())

        return builder.build()
    }

}