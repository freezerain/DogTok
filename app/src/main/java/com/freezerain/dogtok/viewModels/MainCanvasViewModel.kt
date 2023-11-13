package com.freezerain.dogtok.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freezerain.dogtok.data.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCanvasViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _image = MutableLiveData<Bitmap>()
    val image: LiveData<Bitmap> get() = _image

    init {
        nextImage()
    }
    //TODO Potential NULL??? Investigate
    fun nextImage() = viewModelScope.launch {
        repo.imageProducer.receive()?.let { _image.value = it }
    }

}