package com.freezerain.dogtok.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

interface ImageLoader {
    suspend fun loadImage(url: String): Drawable?
    fun loadImage(url: String, target: Target)
}


class PicassoImageLoader : ImageLoader {

    override fun loadImage(url: String, target: Target) {
        Picasso.get().load(url).into(target)
    }

    override suspend fun loadImage(url: String): Drawable? {
        TODO("Not yet implemented")
    }
}

class GlideImageLoader(private val context: Context) : ImageLoader {

    override suspend fun loadImage(url: String): Drawable? {
        return try {
            Glide.with(context)
                .asDrawable()
                .load(url)
                .submit()
                .get()
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "ERROR in loadImage: $e")
            null
        }
    }

    override fun loadImage(url: String, target: Target) {
        TODO("Not yet implemented")
    }
}