package com.freezerain.dogtok

import android.graphics.Bitmap
import kotlinx.coroutines.channels.ReceiveChannel

interface Repo {
    val imageProducer : ReceiveChannel<Bitmap>
}