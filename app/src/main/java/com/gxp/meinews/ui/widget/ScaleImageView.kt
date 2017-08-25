package com.gxp.meinews.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * Created by SparkYuan on 8/10/2016.
 * Github: github.com/SparkYuan
 */
class ScaleImageView : ImageView {
    private var originalWidth: Float = 0.toFloat()
    private var originalHeight: Float = 0.toFloat()

    constructor(context: Context) : super(context)

    constructor(context: Context, width: Int, height: Int) : super(context) {
        originalHeight = height.toFloat()
        originalWidth = width.toFloat()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOriginalSize(originalWidth: Float, originalHeight: Float) {
        this.originalWidth = originalWidth
        this.originalHeight = originalHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (originalWidth > 0 && originalHeight > 0) {
            val ratio = originalWidth / originalHeight
            val width = View.MeasureSpec.getSize(widthMeasureSpec)
            var height = View.MeasureSpec.getSize(heightMeasureSpec)
            if (width > 0) {
                height = (width / ratio).toInt()
            }
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
