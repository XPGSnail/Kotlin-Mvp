package com.gxp.meinews.imageload

import android.text.TextUtils
import com.facebook.drawee.drawable.ScalingUtils


/**
 * Created by pandaguo on 2017/12/6.
 */
class ImageLoadConfig(builder: ImageLoadBuilder) {

    /**
     * 加载图片的url
     */
    private var mUrl: String? = null
    /**
     * 加载图片的宽,推荐指定宽
     */
    val width: Int
    /**
     * 加载图片的高,推荐指定高
     */
    val height: Int
    /**
     * 占位图资源
     */
    val defImageRes: Int
    /**
     * 是否是角标加载，主要用于区分缓存
     */
    val isSmall: Boolean
    /**
     * 类比ImageView的ScaleType
     */
    val scaleType: ScalingUtils.ScaleType
    /**
     * 是否缓存改图片
     */
    val isCache: Boolean
    /**
     * 是否调整图片
     */
    val isReszieOptiopn: Boolean
    /**
     * 是否圆环显示图拍呢
     */
    val isCircle: Boolean
    /**
     * 圆角显示、圆角的大小
     */
    val roundRadius: Int
    /**
     * 圆环边角的颜色,防止加载不出来需要给定一个跟背景色相同的阴影
     */
    val ovelColor: Int

    /**
     * background资源
     */
    val bgRes: Int

    /**
     * 边角线
     */
    val borderColor: Int
    /**
     * 边角线宽
     */
    val borderWidth: Int
    /**
     * 最顶层覆盖的图片
     */
    val ovelImgRes: Int


    val url: String
        get() {
            if (TextUtils.isEmpty(mUrl)) {
                mUrl = StringBuilder()
                        .append("res://com.pptv.launcher/")
                        .append(defImageRes)
                        .toString()
            }
            return mUrl!!
        }


    init {
        this.mUrl = builder.getUrl()
        this.width = builder.getWidth()
        this.height = builder.getHeight()
        this.defImageRes = builder.getDefImageRes()
        this.isSmall = builder.isSmall()
        this.scaleType = builder.getScaleType()
        this.isCache = builder.isCache()
        this.isReszieOptiopn = builder.getResizeOption()
        this.isCircle = builder.isCircle()
        this.roundRadius = builder.getRoundRadius()
        this.ovelColor = builder.getOvelColor()
        this.bgRes = builder.getBgRes()
        this.borderColor = builder.getBorderColor()
        this.borderWidth = builder.getBorderWidth()
        this.ovelImgRes = builder.getOvelImgRes()
    }

    companion object {

        /**
         * 通过url创建包含加载图片信息的ImageLoadBuilder
         *
         * @param url
         * @return
         */
        fun newImageLoadBuilder(url: String): ImageLoadBuilder {
            return ImageLoadBuilder().setUrl(url)
        }

        /**
         * 通过资源文件的id创建包含加载图片信息的ImageLoadBuilder
         *
         * @param resId
         * @return
         */
        fun newImageLoadBuilder(resId: Int): ImageLoadBuilder {
            return ImageLoadBuilder().setUrl(StringBuilder().append("res://com.pptv.launcher/").append(resId).toString())
        }
    }
}