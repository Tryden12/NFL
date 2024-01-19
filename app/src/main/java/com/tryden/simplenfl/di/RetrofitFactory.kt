package com.tryden.simplenfl.di

import com.squareup.moshi.Moshi
import dagger.assisted.AssistedFactory


/**
 * To utilize more than one api base url, I've created a factory for Retrofit.
 * This interface assists in the RetrofitBuilder() class.
 * It is called to action in the NetworkModule object.
 */
@AssistedFactory
interface RetrofitFactory {
    fun create(baseUrl: String, moshi: Moshi): RetrofitBuilder
}
