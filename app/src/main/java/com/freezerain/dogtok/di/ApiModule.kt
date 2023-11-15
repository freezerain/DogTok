package com.freezerain.dogtok.di

import com.freezerain.dogtok.data.dogApi.ApiRepo
import com.freezerain.dogtok.data.dogApi.ApiRepositoryImpl
import com.freezerain.dogtok.data.dogApi.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/*  This module contains dependencies
*   that are usually a wrapper around
*   API call pipeline
* */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


    @Provides
    fun provideApiRepository(apiService: ApiService): ApiRepo =
        ApiRepositoryImpl(apiService)
}