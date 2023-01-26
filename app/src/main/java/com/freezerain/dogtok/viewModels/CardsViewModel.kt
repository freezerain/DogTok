package com.freezerain.dogtok.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freezerain.dogtok.data.DogModel
import com.freezerain.dogtok.data.MainProducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BUFFER_SIZE: Int = 3

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val mainProducer: MainProducer
) : ViewModel() {
    val items = mutableStateListOf<DogModel>()

    fun loadNext() =
        viewModelScope.launch { items.add(mainProducer.dogModelProducer.receive()) }

    fun requestByIndex(targetIndex: Int) {
        if (targetIndex >= (items.size - BUFFER_SIZE)) {
            Log.d(javaClass.simpleName, "requestByIndex: requesting index $targetIndex with items length ${items.size}")
            loadNext()
        }
    }


}

