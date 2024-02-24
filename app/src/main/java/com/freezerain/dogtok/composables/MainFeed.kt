package com.freezerain.dogtok.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.freezerain.dogtok.data.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainFeed(
    modifier: Modifier = Modifier
) {
    val localModifier = Modifier.padding(5.dp)
    val coroutineScope = rememberCoroutineScope()
    val paddingValues = PaddingValues(5.dp)
    val tabs = remember {
        listOf(
            TabItem(
                title = "Reactive pager",
                icon = Icons.Filled.FavoriteBorder,
                screen = { ReactivePager() }),
            TabItem(
                title = "Foundation pager",
                icon = Icons.Filled.Build,
                screen = { MainPager() }),
        )
    }
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    ) {
        tabs.size
    }

    Column(
        modifier = localModifier.then(modifier).then(Modifier.padding(paddingValues))
    ) {
        TabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,

        ) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = {
                        Text(
                            text = tabItem.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    //icon = { Icon(tabItem.icon, "") }
                )
            }
        }
        HorizontalPager(
            state = pagerState
        ) { page ->
            tabs[page].screen()
        }

    }
}


