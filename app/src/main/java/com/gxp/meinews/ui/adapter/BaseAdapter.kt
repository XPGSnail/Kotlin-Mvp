package com.gxp.meinews.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseViewHolder
import com.gxp.meinews.R

/**
 * Created by pandaGuo on 2017/8/25.
 */
class BaseAdapter<T>(private val mDatas: ArrayList<T>) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.fragment_android, parent)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        holder!!.setData(mDatas,mDatas[position], position)
    }

    override fun getItemCount(): Int = if (mDatas == null || mDatas.isEmpty()) 0 else mDatas.size

    fun addDatas(mDatas: List<T>){
        this.mDatas.addAll(mDatas)
        notifyDataSetChanged()
    }
}

private fun <T> BaseViewHolder.setData(mDatas: ArrayList<T>, t: T, position: Int) {
}

