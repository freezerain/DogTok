package com.freezerain.dogtok.data.dogApi

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepo {

    override fun getRandom(): Flow<ApiResponse> = flow {
        val response = apiService.getRandom()
        emit(response)

    }.onStart { Log.d(javaClass.simpleName, "getRandom: flow started") }
        .onEach { response ->
            Log.d(
                javaClass.simpleName,
                "getRandom: response status: ${response.status}, message: ${response.message}"
            )
        }.catch { e -> Log.e(javaClass.simpleName, "error in getRandom: " + e.message) }
}
