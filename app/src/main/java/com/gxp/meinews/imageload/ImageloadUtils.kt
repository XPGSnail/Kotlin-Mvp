package com.gxp.meinews.imageload

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.memory.MemoryTrimType
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeController
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.common.RotationOptions
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImagePipelineFactory
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.gxp.meinews.Utils.AppUtils


/**
 * Created by pandaguo on 2017/12/6.
 */
object ImageloadUtils {


    fun init(ctx: Context) {
        //普通图片缓存配置
        val disCache = DiskCacheConfig.newBuilder(ctx)
                .setMaxCacheSize(20 * ByteConstants.MB.toLong())
                .setBaseDirectoryName("meiNews")
                .setBaseDirectoryPathSupplier { ctx.cacheDir }
                .build()

        val smallDisCache = DiskCacheConfig.newBuilder(ctx)
                .setBaseDirectoryPathSupplier { ctx.cacheDir }
                .setBaseDirectoryName("meiNewsSamll")
                .setMaxCacheSize(20 * ByteConstants.MB.toLong())
                .setMaxCacheSizeOnLowDiskSpace(10 * ByteConstants.MB.toLong())
                .setMaxCacheSizeOnVeryLowDiskSpace(5 * ByteConstants.MB.toLong())
                .build()

        val memoryTrimmable: NoOpMemoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance()
        memoryTrimmable.registerMemoryTrimmable { trimType ->
            return@registerMemoryTrimmable
            when {(trimType.suggestedTrimRatio == MemoryTrimType.OnCloseToDalvikHeapLimit.suggestedTrimRatio
                    || trimType.suggestedTrimRatio == MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.suggestedTrimRatio
                    || trimType.suggestedTrimRatio == MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.suggestedTrimRatio)
            -> ImagePipelineFactory.getInstance().imagePipeline.clearMemoryCaches()
            }
        }
        val am: ActivityManager = ctx.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val build = ImagePipelineConfig.newBuilder(ctx)
                .setMainDiskCacheConfig(disCache)
                .setSmallImageDiskCacheConfig(smallDisCache)
                .setDownsampleEnabled(true)
                .setBitmapMemoryCacheParamsSupplier(MyBitmapMemoryCacheParamsSupplier(am))
                .setMemoryTrimmableRegistry(memoryTrimmable)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .build()

        Fresco.initialize(ctx, build)
    }

    /**
     * 加载图片
     *
     * @param draweeView
     * @param imageLoadConfig
     */
    fun load(imageLoadConfig: ImageLoadConfig, draweeView: SimpleDraweeView) {
        val request = createImageRequest(imageLoadConfig)
        val controller = createPipelineDraweeController(imageLoadConfig, draweeView, request)
        configHierarchy(imageLoadConfig, draweeView)
        draweeView.controller = controller
        if (imageLoadConfig.bgRes != 0) {
            draweeView.setBackgroundResource(imageLoadConfig.bgRes)
        }
    }

    /**
     * 配置图片加载中的圆环、占位图等属性
     * @param imageLoadConfig
     * @param draweeView
     */
    private fun configHierarchy(imageLoadConfig: ImageLoadConfig, draweeView: SimpleDraweeView) {
        val hierarchy = draweeView.hierarchy
        //图片的Scale
        hierarchy.actualImageScaleType = imageLoadConfig.scaleType
        //占位图
        var res = imageLoadConfig.defImageRes
        if (res != 0) {
            hierarchy.setPlaceholderImage(res)
        }
        if (imageLoadConfig.ovelImgRes != 0) {
            hierarchy.setOverlayImage(AppUtils.getDrawable(imageLoadConfig.ovelImgRes))
        }
        if (imageLoadConfig.isCircle) {
            //圆形
            setCircle(hierarchy, imageLoadConfig)
        } else if (imageLoadConfig.roundRadius > 0) {
            //圆角
            setRound(hierarchy, imageLoadConfig)
        }
    }

    /**
     * 初始化加载中的控制层
     * @param imageLoadConfig
     * @param draweeView
     * @param request
     * @return
     */
    private fun createPipelineDraweeController(imageLoadConfig: ImageLoadConfig, draweeView: SimpleDraweeView, request: ImageRequest): PipelineDraweeController {
        val pipelineDraweeControllerBuilder = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.controller)
                .setAutoPlayAnimations(true)//自动播放gif动画
        return pipelineDraweeControllerBuilder.build() as PipelineDraweeController
    }

    /**
     * 创建加载中的逻辑业务层
     * @param imageLoadConfig
     * @return
     */
    private fun createImageRequest(imageLoadConfig: ImageLoadConfig): ImageRequest {
        val parse = Uri.parse(imageLoadConfig.url)
        //todo 这里以后可以考虑自己写  ImageDecoderOptions
        val imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(parse)
                //                .setProgressiveRenderingEnabled(true)//图片渐进式加载 png图片渐进加载有坑
                .setLocalThumbnailPreviewsEnabled(true)//缩略图展示
                .setCacheChoice(if (imageLoadConfig.isSmall)
                    ImageRequest.CacheChoice.SMALL
                else
                    ImageRequest.CacheChoice.DEFAULT)
                .setRotationOptions(RotationOptions.autoRotate())//旋转角度补偿
        var width = imageLoadConfig.width
        var height = imageLoadConfig.height
        //初始压缩的宽、高
        //配置压缩比
        if (imageLoadConfig.isReszieOptiopn) {
            if (width > 0 && height > 0) {
                imageRequestBuilder.resizeOptions = ResizeOptions(width, height)
            }
        }
        //缓存存放位置
        if (!imageLoadConfig.isCache) {
            imageRequestBuilder.disableDiskCache()
        }
        return imageRequestBuilder.build()
    }

    /**
     * 圆环显示
     *
     * @param hierarchy
     * @param imageLoadConfig
     */
    fun setCircle(hierarchy: GenericDraweeHierarchy, imageLoadConfig: ImageLoadConfig) {
        //gif无法显示圆角
        val roundingParams = RoundingParams.asCircle()
        if (imageLoadConfig.ovelColor != 0) {
            roundingParams.overlayColor = imageLoadConfig.ovelColor
        }
        roundingParams.borderWidth = imageLoadConfig.borderWidth.toFloat()
        if (imageLoadConfig.borderColor != 0) {
            roundingParams.borderColor = imageLoadConfig.borderColor
        }
        //遮罩层
        hierarchy.roundingParams = roundingParams
    }

    /**
     * 圆环显示
     */
    fun setRound(hierarchy: GenericDraweeHierarchy, imageLoadConfig: ImageLoadConfig) {
        //gif无法显示圆角
        val roundingParams = RoundingParams.fromCornersRadius(imageLoadConfig.roundRadius.toFloat())
        //遮罩层
        if (imageLoadConfig.ovelColor != 0) {
            roundingParams.overlayColor = imageLoadConfig.ovelColor
        }
        roundingParams.borderWidth = imageLoadConfig.borderWidth.toFloat()
        if (imageLoadConfig.borderColor != 0) {
            roundingParams.borderColor = imageLoadConfig.borderColor
        }
        hierarchy.roundingParams = roundingParams
    }


    /**
     * 暂停网络请求
     */
    fun pause() {
        Fresco.getImagePipeline().pause()
    }

    /**
     * 恢复网络请求
     */
    fun resume() {
        Fresco.getImagePipeline().resume()
    }
}


