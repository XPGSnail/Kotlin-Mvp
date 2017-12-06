package com.gxp.meinews.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.SimpleDraweeView
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.imageload.ImageLoadBuilder
import com.gxp.meinews.imageload.ImageloadUtils

/**
 * Created by panda.guo on 2017/5/17.
 */

class MeiZiAdapter(data: List<GankGoods>?) : BaseQuickAdapter<GankGoods, BaseViewHolder>(R.layout.item_meizi, data) {

    override fun convert(helper: BaseViewHolder, item: GankGoods) {
        val imageView = helper.itemView.findViewById<SimpleDraweeView>(R.id.imageView) as SimpleDraweeView
        ImageloadUtils.load(ImageLoadBuilder()
                .setUrl(item.url)
                .setScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build(),imageView)

    }


}
