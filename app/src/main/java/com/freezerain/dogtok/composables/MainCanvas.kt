package com.freezerain.dogtok.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.freezerain.dogtok.MainCanvasViewModel
import com.freezerain.dogtok.R

@Composable
fun MainCanvas(modifier: Modifier = Modifier) {
    val localModifier = Modifier
    val viewModel = MainCanvasViewModel()
    val placeholderImg = painterResource(id = R.drawable.ph_cat)
    val image by viewModel.image.observeAsState()

    Column(localModifier.then(modifier)) {

        image?.let{Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "DOG", contentScale = ContentScale.Fit, modifier = Modifier.weight(1.0f, true).fillMaxSize()
        )}
        Button(onClick = { onLoadDog(viewModel) }) {
            Text(text = "Load dog")
        }
    }
}

@Preview
@Composable
fun MainCanvasPreview() {
    val img = painterResource(id = R.drawable.ph_cat)
    Column {
        Image(img, contentDescription = "a cat!", modifier = Modifier.weight(1.0f, true).fillMaxSize())
        Button(onClick = {  }) {
            Text(text = "Load dog")
        }
    }
}

fun onLoadDog(viewModel: MainCanvasViewModel) {
    viewModel.nextUrl()
}