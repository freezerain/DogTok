package com.freezerain.dogtok.viewModels

import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.freezerain.dogtok.data.dogApi.ApiRepositoryImpl
import com.freezerain.dogtok.util.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/*
* Потому что композабл не имеет лайф цикла мне надо
* создать вьюмодел для пейджера а не каждой карты
* этот вьюмодел подготовит StateFlow в который будет емитить дату
* этот стейт флоу композабл должен обсервить asState
* и результат просто передаватся в параметры УИ
*
* */

@HiltViewModel
class CardViewModel @Inject constructor(
    private val apiRepositoryImpl: ApiRepositoryImpl, private val imageLoader: ImageLoader
) : ViewModel() {

    val imageState = mutableStateOf<Drawable?>(null)
    val urlState = mutableStateOf<String?>(null)

    init {
        //fetchData()
    }

    /*fun fetchData() {
        viewModelScope.launch {
            try {
                Log.d(javaClass.simpleName, "fetchData: start")
                val apiResponse = withContext(Dispatchers.IO) {
                    apiRepositoryImpl.fetchData()
                }
                Log.d(javaClass.simpleName, "fetchData: $apiResponse")
                urlState.value = apiResponse.message
                Log.d(javaClass.simpleName, "fetchData: start loading image")
                apiResponse.message.let { imageUrl ->
                    val drawable = withContext(Dispatchers.IO) {
                        imageLoader.loadImage(imageUrl)
                    }
                    Log.d(javaClass.simpleName, "fetchData: set image to muteableState")
                    imageState.value = drawable
                }
            } catch (e: Exception) {
                Log.e(javaClass.simpleName, "fetchData: ${e.message}")
            }
        }
    }*/
}