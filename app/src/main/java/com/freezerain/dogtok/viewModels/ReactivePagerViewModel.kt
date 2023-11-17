package com.freezerain.dogtok.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.freezerain.dogtok.data.dogApi.DogPagingSource
import com.freezerain.dogtok.util.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
@HiltViewModel
class ReactivePagerViewModel @Inject constructor(
    private val dogPagingSource: DogPagingSource,
    val imageLoader: ImageLoader
) : ViewModel() {
    val flow = Pager(PagingConfig(
        pageSize = 5, enablePlaceholders = true,
    )) {
        dogPagingSource
    }.flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)
}