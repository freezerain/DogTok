package com.freezerain.dogtok.data

import android.graphics.Bitmap
import kotlinx.coroutines.channels.ReceiveChannel

interface Repo {
    val imageProducer : ReceiveChannel<Bitmap>
}