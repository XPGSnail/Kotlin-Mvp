package com.gxp.meinews.ui.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.ui.widget.ScaleImageView

/**
 * Created by panda.guo on 2017/5/17.
 */

class MeiZiAdapter(data: List<GankGoods>?) : BaseQuickAdapter<GankGoods, BaseViewHolder>(R.layout.item_meizi, data) {

    override fun onBindViewHolder(holder: BaseViewHolder, positions: Int) {
        super.onBindViewHolder(holder, positions)
    }

    override fun convert(helper: BaseViewHolder, item: GankGoods) {
        val imageView = helper.itemView.findViewById<ScaleImageView>(R.id.imageView) as ScaleImageView
        imageView.setOriginalSize(item.width, item.height)
        Glide.with(mContext)
                .load(item.url)
                .apply(RequestOptions().centerCrop())
                .into(imageView)
    }


}