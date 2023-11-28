package com.freezerain.dogtok.data.dogApi

import android.graphics.drawable.Drawable
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.freezerain.dogtok.util.ImageLoader
import com.freezerain.dogtok.util.ImageLoadingException
import retrofit2.HttpException
import retrofit2.http.HTTP
import java.io.IOException
import javax.inject.Inject

class DogPagingSource @Inject constructor(
    private val apiService: ApiService,
    private val imageLoader: ImageLoader
) : PagingSource<Int, Drawable>() {
  override suspend fun load(
    params: LoadParams<Int>
  ): LoadResult<Int, Drawable> {
    return try {
      val pageNumber = params.key ?: 0
      val response = apiService.getRandom()
      val image:Drawable = run{
            var callbackResult:Drawable?= null
            imageLoader.loadImage(response.message){drawable -> callbackResult = drawable}
            callbackResult!!
      }

      LoadResult.Page(
        data = listOf(image),
        prevKey = if (pageNumber > 0) pageNumber - 1 else null,
        nextKey = pageNumber + 1
      )
    } catch (e: Exception) {
        when(e) {
            // TODO Review exception types
            is IOException, is HttpException, is NullPointerException, is ImageLoadingException -> {
              LoadResult.Error(e)
            }
            else -> throw e
        }
    }
  }


  override fun getRefreshKey(state: PagingState<Int, Drawable>): Int? {
    // Find closest page
    // previous +1 or next -1 or null for initial page
    return state.anchorPosition?.let {
      val anchorPage = state.closestPageToPosition(it)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}