package com.freezerain.dogtok.data.dogApi

import retrofit2.http.GET


interface ApiService {

    @GET("/api/breeds/image/random")
    suspend fun getRandom(): ApiResponse // Update with your actual API endpoint and response type
}