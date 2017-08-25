package com.gxp.meinews.base

/**
 * Created by pandaGuo on 2017/8/23.
 */
class GankGoods(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String
) {

    fun create() = createdAt.substring(0,10)
}