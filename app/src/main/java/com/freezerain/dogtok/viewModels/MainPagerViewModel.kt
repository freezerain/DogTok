package com.freezerain.dogtok.viewModels

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freezerain.dogtok.data.dogApi.ApiService
import com.freezerain.dogtok.util.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class MainPagerViewModel @Inject constructor(
	private val apiService: ApiService, private val imageLoader: ImageLoader
) : ViewModel() {
	private val _images = MutableStateFlow<List<Drawable>>(emptyList())
	val images: StateFlow<List<Drawable>> = _images
	val jobMutex = Mutex()

	init {
		loadImages(5)
	}

	fun loadImages(count:Int = 3) {
		viewModelScope.launch {
			jobMutex.withLock {
				flow {
					repeat(count) {
						emit(apiService.getRandom())
					}
				}.onStart {Log.d(javaClass.simpleName, "loadImages: start flow")}
					.onEach {url ->
						Log.d(javaClass.simpleName, "loadImages: emitted: $url")
					}
					.map {apiResponse -> imageLoader.loadImage(apiResponse.message)!!}
					//.buffer(capacity = 5)
					.retry(3)
					.catch {
						Log.e(javaClass.simpleName, "error in loadImages: $it")
					}.onCompletion {
						Log.d(javaClass.simpleName,
							"loadImages: complete flow, reason: $it")}
					.flowOn(Dispatchers.IO)
					.collect {drawable -> _images.emit(_images.value + drawable)}
			}
		}
	}
}