package com.freezerain.dogtok.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freezerain.dogtok.viewModels.CardViewModel


@Composable
fun Card(
    modifier: Modifier = Modifier,
    title: String = "Its a DOG",
    description: String = "Very good boy",
    viewModel: CardViewModel = viewModel()
) {
    val image = viewModel.imageState
    Box(modifier) {
        viewModel.imageState.value?.toBitmap()?.let {
            Image(
                bitmap = it.asImageBitmap(), contentDescription = "DOG",
                modifier = Modifier.fillMaxSize(), alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Text(
                text = extractBreedFromUrl(viewModel.urlState.value ?: "no url:("),
                fontSize = 28.sp, color = Color(1f, 0.1f, 0.1f, 1f)
            )
            Text(text = description, fontSize = 14.sp, color = Color(1f, 0.1f, 0.1f, 1f))
        }
    }

}

private fun extractBreedFromUrl(url: String): String {
    //return url.substring(url.lastIndexOf("breeds/") + 7, url.lastIndexOf('/')).replace('-', ' ')
    return url;
}

