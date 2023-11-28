package com.freezerain.dogtok.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.log
import com.freezerain.dogtok.data.dogApi.DogPagingSource
import com.freezerain.dogtok.util.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

@HiltViewModel
class ReactivePagerViewModel @Inject constructor(
    private val dogPagingSource: DogPagingSource, val imageLoader: ImageLoader
) : ViewModel() {
    val flow = Pager(PagingConfig(
        initialLoadSize = 5,
        pageSize = 1,
        //jumpThreshold = 1,
        enablePlaceholders = true,
        prefetchDistance = 3,
        //maxSize = 3
    )) {
        dogPagingSource
    }.flow//.onEach {pd-> if(pd.)Log.d(javaClass.simpleName, ": ")}
        //.retry(3)
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)
}