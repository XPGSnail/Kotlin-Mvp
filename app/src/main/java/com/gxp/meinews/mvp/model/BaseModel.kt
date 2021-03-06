package com.gxp.meinews.mvp.model

import com.gxp.meinews.api.GankApi
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.base.HttpResult
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.ui.fragment.AndroidFragment
import com.gxp.meinews.ui.fragment.GirlFragment
import com.gxp.meinews.ui.fragment.IOSFragment
import rx.Observable
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/24.
 */
class BaseModel @Inject
    constructor(private var gankApi: GankApi):BaseContract.IModel {
    override fun getData(page: Int, type: String): Observable<HttpResult<List<GankGoods>>> {
        return when (type) {
            AndroidFragment.ANDROID -> {
                gankApi.getAndroidData(page)
            }
            IOSFragment.IOS -> {
                gankApi.getiOSData(page)
            }
            GirlFragment.GIRL -> {
                gankApi.getGirlData(page)
            }
            else ->{
                gankApi.getAndroidData(page)
            }
        }
    }
}