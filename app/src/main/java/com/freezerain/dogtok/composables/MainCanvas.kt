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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freezerain.dogtok.MainCanvasViewModel


@Composable
fun MainCanvas(
    modifier: Modifier = Modifier,
    viewModel: MainCanvasViewModel = viewModel()
) {
    val localModifier = Modifier
    Column(localModifier.then(modifier)) {
        val image by viewModel.image.observeAsState()
        image?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "DOG", contentScale = ContentScale.Fit, modifier = Modifier
                    .weight(1.0f, true)
                    .fillMaxSize()
            )
        }
        Button(onClick = { viewModel.nextImage() }) {
            Text(text = "Load dog")
        }
    }
}