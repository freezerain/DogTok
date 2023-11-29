package com.freezerain.dogtok.di

import android.net.TrafficStats
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/*  This module contains dependencies
*   that are not used directly but consumed by
*   other dependencies
*/
@Module
@InstallIn(SingletonComponent::class)
class BackboneModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
            // TODO Trying to get rid of socket tag violation
            // wth is happening here
            TrafficStats.setThreadStatsTag(Thread.currentThread().id.toInt())
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()


}