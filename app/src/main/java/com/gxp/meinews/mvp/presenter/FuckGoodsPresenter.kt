package com.gxp.meinews.mvp.presenter

import android.util.Log
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.model.BaseModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class FuckGoodsPresenter @Inject
constructor(private val mModel:BaseModel,
            private val mView:BaseContract.IView):BasePresenter(),IPresenter {

    override fun getData(page: Int, type: String) {
        addSubscription(mModel.getData(page,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    res->
                    if(!res.error)
                        mView.setData(res.result)
                },{e ->
                    Log.e("getData","error occur when getData:"+e.message)
                }))
    }
}