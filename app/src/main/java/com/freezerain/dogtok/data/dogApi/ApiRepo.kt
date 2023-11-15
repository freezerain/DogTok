package com.freezerain.dogtok.data.dogApi

import kotlinx.coroutines.flow.Flow

interface ApiRepo {
    fun getRandom(): Flow<ApiResponse>
}