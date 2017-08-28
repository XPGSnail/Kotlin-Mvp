package com.gxp.meinews.mvp.contract

import android.content.Context
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.base.HttpResult
import rx.Observable

/**
 * Created by pandaGuo on 2017/8/23.
 */
interface BaseContract {
    interface IView {
        fun setData(results: List<GankGoods>)
        fun showLoading()
        fun showLoadingMore()
        fun addData(result: List<GankGoods>)
        fun hideLoadingMore(b: Boolean)
        fun hideLoading()
        fun getContext(): Context
    }
    interface IModel{
        fun getData(page: Int,type:String): Observable<HttpResult<List<GankGoods>>>
    }

}