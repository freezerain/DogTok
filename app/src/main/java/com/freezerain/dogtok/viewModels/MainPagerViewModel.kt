package com.freezerain.dogtok.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freezerain.dogtok.data.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BUFFER_SIZE: Int = 3

@HiltViewModel
class MainPagerViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    val items = mutableStateListOf<ImageBitmap>()

    fun loadNext() =
        viewModelScope.launch { items.add(repo.imageProducer.receive().asImageBitmap()) }

    fun requestByIndex(targetIndex: Int) {
        if (targetIndex >= (items.size - BUFFER_SIZE)) {
            Log.d(javaClass.simpleName, "requestByIndex: requesting index $targetIndex with items length ${items.size}")
            loadNext()
        }
    }


}

