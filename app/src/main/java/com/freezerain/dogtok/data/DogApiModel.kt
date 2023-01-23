package com.freezerain.dogtok.data

import com.google.gson.annotations.SerializedName

class DogApiModel {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null
}