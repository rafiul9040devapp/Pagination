package com.walletmix.pagination.di

import com.rafiul.basiclib.NativeLib
import com.walletmix.pagination.api.QuotesApi
import com.walletmix.pagination.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getUrl(): String {
        return NativeLib().stringFromJNI()
    }

    @Singleton
    @Provides
    fun getRetrofitBuilder(url: String): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun getQuotesApi(retrofit: Retrofit.Builder): QuotesApi {
        return retrofit.build().create(QuotesApi::class.java)
    }
}