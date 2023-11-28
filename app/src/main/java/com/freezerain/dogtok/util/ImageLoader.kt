package com.freezerain.dogtok.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.HttpException
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.CancellationException
import java.util.concurrent.ExecutionException

interface ImageLoader {
    suspend fun loadImage(url: String, callback: (Drawable) -> Unit)
    fun loadImage(url: String, target: Target)
}

class PicassoImageLoader : ImageLoader {
    override fun loadImage(url: String, target: Target) {
        Picasso.get()
            .load(url)
            .into(target)
    }

    override suspend fun loadImage(url: String, callback: (Drawable) -> Unit) {
        TODO("Not yet implemented")
    }
}

class GlideImageLoader(private val context: Context) : ImageLoader {
    override suspend fun loadImage(url: String, callback: (Drawable) -> Unit) {
        try {
            callback(withContext(Dispatchers.IO) {
                Glide.with(context)
                    .load(url)
                    .placeholder(android.R.drawable.stat_sys_download)
                    .error(android.R.drawable.stat_notify_error)
                    //.fallback(android.R.drawable.stat_notify_sync)
                    //TODO Remove test delay
                    .submit()
                    .apply{delay(500)}
                    .get()
            })
        } catch (e: Exception) {
            when (e) {
                is IOException, is CancellationException, is ExecutionException -> {
                    throw ImageLoadingException("Error loading image using Glide", e)
                }
                else -> throw e
            }
        }
    }

    override fun loadImage(url: String, target: Target) {
        TODO("Not yet implemented")
    }
}