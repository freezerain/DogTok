package com.freezerain.dogtok.data

import android.util.Log
import androidx.compose.ui.graphics.asImageBitmap
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import retrofit2.HttpException
import javax.inject.Inject

private const val BUFFER_CAPACITY = 4

class MainProducer @Inject constructor(
    private val dogApi: DogApi
){

    //Build an infinite image producer that can hold up to capacity+1 images
    @OptIn(ExperimentalCoroutinesApi::class)
    val dogModelProducer = CoroutineScope(Dispatchers.IO).produce(capacity = BUFFER_CAPACITY) {
        while (true) {
            getDogApiUrl()?.let {
                send(DogModel(it,buildImageRequest(it).get().asImageBitmap())) //TODO NO CACHE!!!!!!!
            }
        }
    }

    //Request next random url of the image from dog api
    private suspend fun getDogApiUrl(): String? {
        val response = dogApi.nextUrl()
        try {
            if (response.isSuccessful) {
                Log.d(
                    javaClass.simpleName,
                    "getDogApiUrl success: ${response.code()} ${response.body()?.message}}"
                )
                return response.body()?.message
            } else {
                Log.d(javaClass.simpleName, "getDogApiUrl failure: ${response.code()}")
            }
        } catch (e: HttpException) {
            Log.d(javaClass.simpleName, "getDogApiUrl http exception: ${e.message}")
        } catch (e: Throwable) {
            Log.d(javaClass.simpleName, "getDogApiUrl generic exception: ${e.message}")
        }
        return null
    }

    private fun buildImageRequest(url: String): RequestCreator {
        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        return picasso.load(url)
    }

}
