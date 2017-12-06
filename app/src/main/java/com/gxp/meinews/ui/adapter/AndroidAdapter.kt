package com.gxp.meinews.ui.adapter

import android.text.TextUtils
import android.widget.TextView
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

class AndroidAdapter(data: List<GankGoods>?) : BaseQuickAdapter<GankGoods, BaseViewHolder>(R.layout.item_android, data) {

    override fun onBindViewHolder(holder: BaseViewHolder, positions: Int) {
        super.onBindViewHolder(holder, positions)
    }

    override fun convert(helper: BaseViewHolder, item: GankGoods) {
        val imageView = helper.itemView.findViewById<SimpleDraweeView>(R.id.imageView) as SimpleDraweeView
        val tvTitle = helper.itemView.findViewById<TextView>(R.id.tvTitle) as TextView
        val tvDesc = helper.itemView.findViewById<TextView>(R.id.tvDesc) as TextView
        val images = item.images
        ImageloadUtils.load(ImageLoadBuilder()
                .setUrl(when {
                    images == null || TextUtils.isEmpty(images[0]) -> R.drawable.icon_guide_default.toString()
                    else -> images[0]
                })
                .setDefImageRes(R.drawable.icon_guide_default)
                .setScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build(), imageView)
        tvTitle.text = if (TextUtils.isEmpty(item.who)) "佚名" else item.who
        tvDesc.text = item.desc
    }
}
