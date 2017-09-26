package com.gxp.meinews.mvp.presenter

import android.graphics.BitmapFactory
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gxp.meinews.MeiApp
import com.gxp.meinews.R
import com.gxp.meinews.Utils.AppUtils
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.model.BaseModel
import com.gxp.meinews.rounter.GankClientUri
import com.gxp.meinews.ui.fragment.GirlFragment
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.ExecutionException
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
                        if (GirlFragment.GIRL == type) {
                            dealData(flag, res.results)
                        } else {
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

    private fun dealData(flag: Int, results: List<GankGoods>) {
        addSubscription(Observable.from(results)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    var bitmap = try {
                        Glide.with(MeiApp.instance)
                                .asBitmap()
                                .load(it.url)
                                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                                .into(Int.MAX_VALUE, Int.MAX_VALUE)
                                .get()
                    } catch (e: InterruptedException) {
                        BitmapFactory.decodeResource(MeiApp.instance.resources, R.mipmap.ic_launcher)
                    } catch (e: ExecutionException) {
                        BitmapFactory.decodeResource(MeiApp.instance.resources, R.mipmap.ic_launcher)
                    }
                    val imageViewWidth = (AppUtils.w(mView.getContext())
                            -mView.getContext().resources.getDimensionPixelSize(R.dimen.dip1) * 4) / 2
                    val imageViewHeight: Int = ((imageViewWidth.toDouble() / bitmap.width) * bitmap.height).toInt()
                    it.width = imageViewWidth.toFloat()
                    it.height = imageViewHeight.toFloat()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    when (flag) {
                        GankClientUri.FLAG_REFRESH -> {
                            mView.hideLoading()
                            Log.d("reslut", res.toString())
                            mView.setData(results)
                        }
                        else -> {
                            mView.hideLoadingMore(true)
                            mView.addData(results)
                        }
                    }

                }, { e ->
                    e.printStackTrace()
                }))
    }
}