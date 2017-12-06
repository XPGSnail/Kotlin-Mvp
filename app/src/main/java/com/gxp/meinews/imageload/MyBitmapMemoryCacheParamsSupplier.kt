package com.gxp.meinews.imageload

import android.app.ActivityManager
import android.os.Build
import com.facebook.common.internal.Supplier
import com.facebook.common.util.ByteConstants
import com.facebook.imagepipeline.cache.MemoryCacheParams


/**
 * Created by pandaguo on 2017/12/6.
 */
class MyBitmapMemoryCacheParamsSupplier(val am:ActivityManager):Supplier<MemoryCacheParams>{

    //缓存中图片的最大数量
    private val MAX_CACHE_ENTRIES = 28
    //最多可以缓存的图片数量
    private val MAX_CACHE_AMS_ENTRIES = 128
    //内存缓存中准备清除但尚未被删除的总图片的最大大小
    private val MAX_CACHE_EVICTION_SIZE = 1 * ByteConstants.MB
    //内存缓存中准备清除的总图片的最大数量
    private val MAX_CACHE_EVICTION_ENTRIES = 5

    override fun get(): MemoryCacheParams {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> MemoryCacheParams(getMaxCacheSize(),
                    MAX_CACHE_ENTRIES,
                    MAX_CACHE_EVICTION_SIZE,
                    MAX_CACHE_EVICTION_ENTRIES,
                    MAX_CACHE_EVICTION_SIZE)//内存缓存中单个图片的最大大小
            else -> MemoryCacheParams(
                    getMaxCacheSize(),
                    MAX_CACHE_AMS_ENTRIES,
                    Integer.MAX_VALUE,
                    Integer.MAX_VALUE,
                    Integer.MAX_VALUE)
        }
    }

    private fun getMaxCacheSize(): Int{
        val maxMemory =
                Math.min(am.memoryClass * ByteConstants.MB, Integer.MAX_VALUE);
        return when {
            maxMemory < 32 * ByteConstants.MB -> 4 * ByteConstants.MB
            maxMemory < 64 * ByteConstants.MB -> 6 * ByteConstants.MB
            else -> maxMemory / 5
        }
    }


}