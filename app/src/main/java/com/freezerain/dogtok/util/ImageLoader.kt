package com.freezerain.dogtok.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

interface ImageLoader {
    fun loadImage(url: String): Drawable?
    fun loadImage(url: String, target: Target)
}


class PicassoImageLoader : ImageLoader {

    override fun loadImage(url: String, target: Target) {
        Picasso.get().load(url).into(target)
    }

    override fun loadImage(url: String): Drawable? {
        TODO("Not yet implemented")
    }
}

class GlideImageLoader(private val context: Context) : ImageLoader {

    override fun loadImage(url: String): Drawable? {
        return try {
            Glide.with(context)
                .asDrawable()
                .load(url)
                .submit()
                .get()
        } catch (e: Exception) {
            null
        }
    }

    override fun loadImage(url: String, target: Target) {
        TODO("Not yet implemented")
    }
}