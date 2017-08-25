package com.gxp.meinews.mvp.contract

import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.base.HttpResult
import rx.Observable

/**
 * Created by pandaGuo on 2017/8/23.
 */
interface BaseContract {
    interface IView {
        fun setData(results: List<GankGoods>)
    }
    interface IModel{
        fun getData(page: Int,type:String): Observable<HttpResult<List<GankGoods>>>
    }

}