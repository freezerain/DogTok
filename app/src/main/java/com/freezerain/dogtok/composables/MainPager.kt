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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freezerain.dogtok.viewModels.MainPagerViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPager(
    modifier: Modifier = Modifier,
    mainPagerViewModel: MainPagerViewModel = viewModel()
) {
    val localModifier = Modifier.padding(5.dp)
    val images = mainPagerViewModel.images.collectAsState()
    val pagerState = rememberPagerState(0, 0.0f) {images.value.size}
    Column(localModifier.then(modifier)) {
        VerticalPager(modifier = Modifier
            .weight(1.0f, true)
            .fillMaxSize(), state = pagerState, flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(0)
            )
        ) { page ->
            Card(modifier, image = images.value[page])
        }
        LaunchedEffect(pagerState.currentPage) {
            Log.d(javaClass.simpleName, "MainPager: current page: ${pagerState.currentPage}, images count: ${images.value.size}")
            if(pagerState.currentPage + 3 > images.value.size && !mainPagerViewModel.jobMutex.isLocked){
                mainPagerViewModel.loadImages()
            }
        }
    }
}