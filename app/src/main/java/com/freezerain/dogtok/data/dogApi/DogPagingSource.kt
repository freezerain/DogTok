package com.freezerain.dogtok.data.dogApi

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.freezerain.dogtok.util.ImageLoader
import javax.inject.Inject

class DogPagingSource @Inject constructor(
    private val apiService: ApiService,
    private val imageLoader: ImageLoader
) : PagingSource<Int, String>() {
  override suspend fun load(
    params: LoadParams<Int>
  ): LoadResult<Int, String> {
    try {
      // Start refresh at page 1 if undefined.
      val nextPageNumber = params.key ?: 1
      val response = apiService.getRandom()
      return LoadResult.Page(
        data = listOf(response.message),
        prevKey = null, // Only paging forward.
        nextKey = nextPageNumber + 1
      )
    } catch (e: Exception) {
      return LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, String>): Int? {
    // Try to find the page key of the closest page to anchorPosition from
    // either the prevKey or the nextKey; you need to handle nullability
    // here.
    //  * prevKey == null -> anchorPage is the first page.
    //  * nextKey == null -> anchorPage is the last page.
    //  * both prevKey and nextKey are null -> anchorPage is the
    //    initial page, so return null.
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}