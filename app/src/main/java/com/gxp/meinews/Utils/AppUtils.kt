package com.gxp.meinews.Utils

import android.graphics.Bitmap
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget


object AppUtils {

    /**
     * 判断集合是否为空
     *
     * @param map
     * @param <V></V>,T>
     * @return
     */
    fun <V, T> isEmpty(map: Map<V, T>?): Boolean {
        return map == null || map.size == 0
    }

    /**
     * 判断集合是否为空
     *
     * @param c
     * @param <V>
     * @return
    </V> */
    fun <V> isEmpty(c: Collection<V>?): Boolean {
        return c == null || c.size == 0
    }


    fun loadCircleImage(context: Fragment, url: String, iv: ImageView) {
        Glide.with(context).asBitmap().load(url).apply(RequestOptions().centerCrop())
                .into(object : BitmapImageViewTarget(iv) {
                    override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        iv.setImageDrawable(circularBitmapDrawable)
                    }
                })

    }

}
