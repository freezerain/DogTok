package com.freezerain.dogtok.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.HttpException
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.CancellationException

interface ImageLoader {
    suspend fun loadImage(url: String, callback: (Drawable?) -> Unit)
    fun loadImage(url: String, target: Target)
}

class PicassoImageLoader : ImageLoader {
    override fun loadImage(url: String, target: Target) {
        Picasso.get()
            .load(url)
            .into(target)
    }

    override suspend fun loadImage(url: String, callback: (Drawable?) -> Unit) {
        TODO("Not yet implemented")
    }
}

class GlideImageLoader(private val context: Context) : ImageLoader {
    override suspend fun loadImage(url: String, callback: (Drawable?) -> Unit) {
        try {
            callback(withContext(Dispatchers.IO) {
                Glide.with(context)
                    .load(url)
                    .placeholder(android.R.drawable.stat_sys_download)
                    .error(android.R.drawable.stat_notify_error)
                    .fallback(android.R.drawable.stat_notify_sync)
                    .submit()
                    .get()
            })
        } catch (e: Exception) {
            when (e) {
                is IOException, is HttpException, is CancellationException -> {
                    Log.e(javaClass.simpleName,
                        "${e.javaClass} exception in loadImage: $e")
                    callback(null)
                }

                else -> {
                    Log.e(javaClass.simpleName,
                        "NEW EXCEPTION TYPE exception in loadImage: $e")
                    callback(null)
                }
            }
        }
    }

    override fun loadImage(url: String, target: Target) {
        TODO("Not yet implemented")
    }
}