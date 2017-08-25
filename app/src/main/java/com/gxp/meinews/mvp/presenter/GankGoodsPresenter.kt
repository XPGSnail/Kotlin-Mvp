package com.gxp.meinews.mvp.presenter

import android.util.Log
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.model.BaseModel
import com.gxp.meinews.rounter.GankClientUri
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class GankGoodsPresenter @Inject
constructor(private val mModel: BaseModel,
            private val mView: BaseContract.IView) : BasePresenter(), IPresenter {

    override fun getData(page: Int, type: String, flag: Int) {
        addSubscription(mModel.getData(page, type)
                .doOnSubscribe({
                    when (flag) {
                        GankClientUri.FLAG_REFRESH -> {
                            mView.showLoading()
                        }
                        else -> {
                            mView.showLoadingMore()
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    if (!res.error)
                        when (flag) {
                            GankClientUri.FLAG_REFRESH -> {
                                mView.hideLoading()
                                Log.d("reslut", res.toString())
                                mView.setData(res.results)
                            }
                            else -> {
                                mView.hideLoadingMore(true)
                                mView.addData(res.results)
                            }
                        }

                }, { e ->
                    when (flag) {
                        GankClientUri.FLAG_REFRESH -> {
                            mView.hideLoading()
                        }
                        else -> {
                            mView.hideLoadingMore(false)
                        }
                    }
                    Log.e("getData", "error occur when getData:" + e.message)
                }))
    }
}