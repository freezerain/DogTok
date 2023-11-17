package com.freezerain.dogtok.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.freezerain.dogtok.viewModels.ReactivePagerViewModel

@Composable
fun ReactivePager(
    modifier: Modifier = Modifier,
    reactivePagerViewModel: ReactivePagerViewModel = viewModel()
) {
    val lazyPagingItems = reactivePagerViewModel.flow.collectAsLazyPagingItems()

    LazyColumn {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(text = "Waiting for items to load from the backend",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally))
            }
        }

        items(count = lazyPagingItems.itemCount) {index ->
            val item = lazyPagingItems[index]
            Text("Index=$index", fontSize = 20.sp)
            item?.let{AutoloadingCard(imageLoader = reactivePagerViewModel.imageLoader, url = it)}
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally))
            }
        }
    }
}