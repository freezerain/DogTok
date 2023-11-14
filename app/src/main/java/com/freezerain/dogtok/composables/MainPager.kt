package com.freezerain.dogtok.composables


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPager(
    modifier: Modifier = Modifier,
) {
    val localModifier = Modifier.padding(5.dp)
    Column(localModifier.then(modifier)) {
        //val items = viewModel.items
        val pagerState = rememberPagerState(0,
            0.0f
        ) { 5 }

        VerticalPager(
            modifier = Modifier
                .weight(1.0f, true)
                .fillMaxSize(), state = pagerState, flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(0)
            )
        ) { page ->
            Card(modifier)
        }

       /* LaunchedEffect(pagerState) {
            //if (items.size < 5) repeat(5) {
                //Log.d(javaClass.simpleName, "MainPager: warming up cache with 5 dogs")
                //viewModel.loadNext()
            //}
            snapshotFlow { pagerState.targetPage }.collect { page ->
                //Log.d(javaClass.simpleName, "MainPager: targetPage = $page")
                //viewModel.requestByIndex(page)
            }
        }*/
    }
}