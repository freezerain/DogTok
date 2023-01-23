package com.freezerain.dogtok.data

import com.freezerain.dogtok.data.DogApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DogApi {

    @get:GET("/api/breeds/image/random")
    val dogUrl: Call<DogApiModel?>?

    @GET("/api/breeds/image/random")
    suspend fun nextUrl(): Response<DogApiModel>

}