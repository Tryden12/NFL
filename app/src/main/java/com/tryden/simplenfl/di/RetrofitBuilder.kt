package com.tryden.simplenfl.di

import com.squareup.moshi.Moshi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

/**
 * To utilize more than one api base url, I've created a builder class for Retrofit.
 * It uses the RetrofitFactory interface and is called to action in the NetworkModule object.
 */
class RetrofitBuilder @AssistedInject constructor(
    @Assisted val baseUrl: String,
    @Assisted val moshi: Moshi,
//    val okHttpClient: OkHttpClient
) {
    inline fun <reified T> build(): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .client(okHttpClient)
            .build()
            .create()
    }
}