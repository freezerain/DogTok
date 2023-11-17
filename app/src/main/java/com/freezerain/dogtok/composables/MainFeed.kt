package com.freezerain.dogtok.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun MainFeed (
	modifier: Modifier = Modifier
) {
	val localModifier = Modifier.padding(5.dp)
	Column(localModifier.then(modifier)) {
		ReactivePager()
	}
}
