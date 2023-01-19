package com.tryden.simplenfl.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.simplenfl.util.Constants.BASE_URL_NFL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object NetworkLayer {

    // Converter factory
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    // Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_NFL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Service
    val nflService: NFLService = retrofit.create(NFLService::class.java)

    // Client
    val apiClient = ApiClient(nflService)

}