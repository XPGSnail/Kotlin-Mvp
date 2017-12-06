package com.gxp.meinews.imageload

import com.facebook.common.internal.Preconditions
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.imagepipeline.request.BasePostprocessor
import com.gxp.meinews.BuildConfig


/**
 * Created by pandaguo on 2017/12/6.
 */


class ImageLoadBuilder {
    private var mUrl: String? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mDefImageRes: Int = 0
    private var mIsSmall: Boolean = false
    private var mProcessor: BasePostprocessor? = null
    private var mScaleType: ScalingUtils.ScaleType = ScalingUtils.ScaleType.FIT_XY
    private var isCache = true
    private var mResizeOption = true
    private var mIsCircle = false
    private var mRoundRadius: Int = 0
    private var mOvelColor: Int = 0
    private var mBgRes: Int = 0
    private var mBorderWidth: Int = 0
    private var mBorderColor: Int = 0
    private var mOvelImgRes: Int = 0

    fun setBgRes(bgRes: Int): ImageLoadBuilder {
        mBgRes = bgRes
        return this
    }

    fun setUrl(url: String): ImageLoadBuilder {
        this.mUrl = url
        return this
    }

    fun setWidth(width: Int): ImageLoadBuilder {
        this.mWidth = width
        return this
    }

    fun setHeight(height: Int): ImageLoadBuilder {
        this.mHeight = height
        return this
    }


    fun setDefImageRes(defImageRes: Int): ImageLoadBuilder {
        this.mDefImageRes = defImageRes
        return this
    }

    fun setSmall(small: Boolean): ImageLoadBuilder {
        this.mIsSmall = small
        return this
    }

    fun setProcessor(processor: BasePostprocessor): ImageLoadBuilder {
        this.mProcessor = processor
        return this
    }

    fun setScaleType(scaleType: ScalingUtils.ScaleType): ImageLoadBuilder {
        mScaleType = scaleType
        return this
    }

    fun isCache(cache: Boolean): ImageLoadBuilder {
        isCache = cache
        return this
    }


    fun setResizeOption(resizeOption: Boolean): ImageLoadBuilder {
        mResizeOption = resizeOption
        return this
    }

    fun setCircle(circle: Boolean): ImageLoadBuilder {
        mIsCircle = circle
        return this
    }

    fun setRoundRadius(roundRadius: Int): ImageLoadBuilder {
        mRoundRadius = roundRadius
        return this
    }

    fun setOvelColor(ovelColor: Int): ImageLoadBuilder {
        mOvelColor = ovelColor
        return this
    }

    fun setBorderWidth(borderWidth: Int): ImageLoadBuilder {
        mBorderWidth = borderWidth
        return this
    }

    fun setBorderColor(borderColor: Int): ImageLoadBuilder {
        mBorderColor = borderColor
        return this
    }

    fun setOvelImgRes(ovelImgRes: Int): ImageLoadBuilder {
        mOvelImgRes = ovelImgRes
        return this
    }

    fun build(): ImageLoadConfig {
        //加载的url不能为null
        if (!BuildConfig.DEBUG) {
            Preconditions.checkNotNull(mUrl)
        }
        return ImageLoadConfig(this)
    }

    fun getUrl(): String? {
        return mUrl
    }

    fun getWidth(): Int {
        return mWidth
    }

    fun isCache(): Boolean {
        return isCache
    }

    fun getHeight(): Int {
        return mHeight
    }

    fun getDefImageRes(): Int {
        return mDefImageRes
    }

    fun isSmall(): Boolean {
        return mIsSmall
    }

    fun getScaleType(): ScalingUtils.ScaleType {
        return mScaleType
    }

    fun getResizeOption(): Boolean {
        return mResizeOption
    }

    fun isCircle(): Boolean {
        return mIsCircle
    }

    fun getRoundRadius(): Int {
        return mRoundRadius
    }

    fun getOvelColor(): Int {
        return mOvelColor
    }

    fun getBgRes(): Int {
        return mBgRes
    }

    fun getBorderWidth(): Int {
        return mBorderWidth
    }

    fun getBorderColor(): Int {
        return mBorderColor
    }

    fun getOvelImgRes(): Int {
        return mOvelImgRes
    }
}