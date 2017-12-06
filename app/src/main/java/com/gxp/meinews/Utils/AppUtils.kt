package com.gxp.meinews.Utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.WindowManager
import com.gxp.meinews.MeiApp


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

    internal var height = 0
    internal var width = 0

    internal fun w(context: Context): Int {
        if (width == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            width = outMetrics.widthPixels
        }
        return width
    }

    internal fun h(context: Context): Int {
        if (height == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            height = outMetrics.heightPixels
        }
        return height
    }


    internal fun getDrawable(id:Int): Drawable {
        return MeiApp.instance.resources.getDrawable(id)
    }
}
