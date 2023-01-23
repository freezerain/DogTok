package com.freezerain.dogtok


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freezerain.dogtok.viewModels.MainPagerViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun MainPager(
    modifier: Modifier = Modifier,
    viewModel: MainPagerViewModel = viewModel()
) {
    val localModifier = Modifier.padding(20.dp)
    Column(localModifier.then(modifier)) {
        val state = rememberLazyListState()
        val pagerState = rememberPagerState()
        //val snappingLayout = remember(state) { SnapLayoutInfoProvider(pagerState) }
       // val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
        val items = viewModel.items
        //val temp = state.firstVisibleItemIndex > (items.size - 3)

        VerticalPager(
            pageCount = items.size, modifier = Modifier
                .weight(1.0f, true)
                .fillMaxSize(), state = pagerState, flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(0)
            )
        ) { page ->
            Image(
                bitmap = items[page],
                contentDescription = "DOG",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )

        }

        LaunchedEffect(pagerState){
            if(items.size<5) repeat(5){
                Log.d(javaClass.simpleName, "MainPager: warming up cache with 5 dogs")
                viewModel.loadNext()
            }
            snapshotFlow { pagerState.targetPage }.collect{
                page ->
                Log.d(javaClass.simpleName, "MainPager: targetPage = $page")
                viewModel.requestByIndex(page)
            }
        }
     /*   Button(*//*modifier = localModifier.weight(1.0f, false),*//* onClick = { viewModel.loadNext() }) {
            Text(text = "Load dog")
        }*/
    }
}