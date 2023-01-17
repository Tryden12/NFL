package com.tryden.simplenfl.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.simplenfl.util.Constants.BASE_URL_NFL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_NFL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val nflService: NFLService = retrofit.create(NFLService::class.java)

    val apiClient = ApiClient(nflService)

}