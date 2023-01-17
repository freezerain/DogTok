package com.freezerain.dogtok

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MainCanvasViewModel() : ViewModel() {
    private val _dogUrl = MutableLiveData<String>()
    val dogUrl: LiveData<String> get() = _dogUrl
    private var dogApiService: DogApiInterface? = null

    private val _image = MutableLiveData<Bitmap>()
    val image: LiveData<Bitmap> get() = _image

    init{
        dogApiService = DogApiRetrofitFactory.dogClient?.create(DogApiInterface::class.java)
    }

    fun nextUrl(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = dogApiService?.nextUrl()
            try {
                if (response?.isSuccessful == true) {
                    Log.d(javaClass.simpleName, "nextUrl success: ${response.code()}")
                    Thread.sleep(2000)
                    response.body()?.message?.let { loadImage(it) }
                } else {
                    Log.d(javaClass.simpleName, "nextUrl failure: ${response?.code()}")
                }
            } catch (e: HttpException) {
                Log.d(javaClass.simpleName, "nextUrl exception: ${e.message}")
            } catch (e: Throwable) {
                Log.d(javaClass.simpleName, "nextUrl exception: ${e.message}")

            }
        }
    }

    private fun loadImage(url: String) {
        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        _image.postValue(picasso.load(url).get())
    }
}