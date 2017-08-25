package com.gxp.meinews.ui.adapter

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods

/**
 * Created by panda.guo on 2017/5/17.
 */

class AndroidAdapter(data: List<GankGoods>?) : BaseQuickAdapter<GankGoods, BaseViewHolder>(R.layout.item_android, data) {

    override fun onBindViewHolder(holder: BaseViewHolder, positions: Int) {
        super.onBindViewHolder(holder, positions)
    }

    override fun convert(helper: BaseViewHolder, item: GankGoods) {
        val imageView = helper.itemView.findViewById<ImageView>(R.id.imageView) as ImageView
        val tvTitle = helper.itemView.findViewById<TextView>(R.id.tvTitle) as TextView
        val tvDesc = helper.itemView.findViewById<TextView>(R.id.tvDesc) as TextView
        val images = item.images
        if (images!=null && !TextUtils.isEmpty(images[0])) {
            Glide.with(mContext)
                    .load(images[0])
                    .apply(RequestOptions().centerCrop())
                    .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.icon_guide_default)
        }
        tvTitle.text = if (TextUtils.isEmpty(item.who)) "佚名" else item.who
        tvDesc.text = item.desc
    }

}
