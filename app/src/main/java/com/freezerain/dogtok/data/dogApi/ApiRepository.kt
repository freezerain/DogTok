package com.freezerain.dogtok.data.dogApi

import android.util.Log

class ApiRepository(private val apiService: ApiService) {

    suspend fun fetchData(): ApiResponse {
        Log.d(javaClass.simpleName, "fetchData: start")
        try {
            // Perform API call using Retrofit
            val response = apiService.fetchData()

            // Extract metadata and image URL from the response
            val status = response.status
            val message = response.message
            Log.d(javaClass.simpleName, "fetchData: status: $status, message: $message")
            return ApiResponse(message, status)
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "fetchData: "+e.message)
            throw e // Handle error appropriately
        }
    }
}
