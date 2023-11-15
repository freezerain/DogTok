package com.freezerain.dogtok.di

import android.content.Context
import com.freezerain.dogtok.util.GlideImageLoader
import com.freezerain.dogtok.util.ImageLoader
import com.freezerain.dogtok.util.PicassoImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*  This module contains dependencies
*   that are usually libraries
*   and used directly by code
* */

private const val BASE_URL = "https://dog.ceo"

@Module
@InstallIn(SingletonComponent::class)
class ToolModule {

    @Singleton
    @Provides
    fun provideImageLoader(glideImageLoader: GlideImageLoader): ImageLoader =
        glideImageLoader

    @Singleton
    @Provides
    fun providePicassoImageLoader(): PicassoImageLoader = PicassoImageLoader()


    @Singleton
    @Provides
    fun provideGlideImageLoader(@ApplicationContext context: Context): GlideImageLoader =
        GlideImageLoader(context)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .build()
}