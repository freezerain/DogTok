package com.freezerain.dogtok.composables


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freezerain.dogtok.viewModels.CardsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun MainPager(
    modifier: Modifier = Modifier,
    viewModel: CardsViewModel = viewModel()
) {
    val localModifier = Modifier.padding(5.dp)
    Column(localModifier.then(modifier)) {
        val pagerState = rememberPagerState()
        val items = viewModel.items

        VerticalPager(
            pageCount = items.size, modifier = Modifier
                .weight(1.0f, true)
                .fillMaxSize(), state = pagerState, flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(0)
            )
        ) { page ->
            Card(modifier = modifier, dogModel = items[page])
        }

        LaunchedEffect(pagerState) {
            if (items.size < 5) repeat(5) {
                Log.d(javaClass.simpleName, "MainPager: warming up cache with 5 dogs")
                viewModel.loadNext()
            }
            snapshotFlow { pagerState.targetPage }.collect { page ->
                Log.d(javaClass.simpleName, "MainPager: targetPage = $page")
                viewModel.requestByIndex(page)
            }
        }
    }
}