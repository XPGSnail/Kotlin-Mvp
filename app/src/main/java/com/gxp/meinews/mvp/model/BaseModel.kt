package com.gxp.meinews.mvp.model

import com.gxp.meinews.api.GankApi
import com.gxp.meinews.base.FuckGoods
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
    override fun getData(page: Int, type: String): Observable<HttpResult<List<FuckGoods>>> {
        when (type) {
            AndroidFragment.ANDROID -> {
                return gankApi.getAndroidData(page)
            }
            IOSFragment.IOS -> {
                return gankApi.getiOSData(page)
            }
            GirlFragment.GIRL -> {
                return gankApi.getGirlData(page)
            }
            else ->{
                return gankApi.getAndroidData(page)
            }
        }
    }
}