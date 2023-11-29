package com.freezerain.dogtok.composables

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.freezerain.dogtok.viewModels.ReactivePagerViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReactivePager(
    modifier: Modifier = Modifier,
    reactivePagerViewModel: ReactivePagerViewModel = viewModel()
) {
    val lazyPagingItems = reactivePagerViewModel.flow.collectAsLazyPagingItems()
    val columnState = rememberLazyListState()
    val snappingLayout = remember(columnState) { SnapLayoutInfoProvider(columnState) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    //Show loading for refreshing ALL items
    if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
        Text(text = "Waiting for items to load from the backend",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                fontSize = 30.sp)
    }
    else LazyColumn(modifier = Modifier.fillMaxSize(), state = columnState, flingBehavior = flingBehavior) {

        if(lazyPagingItems.loadState.append is LoadState.Error || lazyPagingItems.loadState.refresh is LoadState.Error){
            Log.e(javaClass.simpleName, "ERROR in ReactivePager: ERROR")
            lazyPagingItems.retry()
        }

        items(count = lazyPagingItems.itemCount) {index ->
            Column(Modifier.fillParentMaxSize()){
                Text("Index=$index", fontSize = 20.sp)
                lazyPagingItems[index]?.let{
                    Card(image = it)
                }
            }
        }

        //Show loading for adding new items
        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            }
        }
    }
}
