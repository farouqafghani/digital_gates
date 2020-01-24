package com.example.mazintask.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Farouq Afghani on 2019-03-19.
 */
object GlideImageUtils {

    fun setImage(
        context: Context?,
        imageToLoad: Any?,
        fastLoadUrl: Any? = null,
        imageView: ImageView?
    ) {
        if (context == null || imageView == null) {
            return
        }

        val requestOption = RequestOptions()
            .fitCenter()
            .placeholder(Utils().getCircularProgressDrawable(context))

        val glideLoader = Glide
            .with(context.applicationContext)
            .load(imageToLoad ?:"")
            .apply(requestOption)


        if (fastLoadUrl != null) {
            glideLoader.thumbnail(
                Glide.with(context.applicationContext)
                    .load(fastLoadUrl)
                    .apply(requestOption)
            ).into(imageView)
            return
        }
        glideLoader.into(imageView)
    }
}
