package com.gxp.meinews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gxp.meinews.MeiApp
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.di.module.GankGoodsModule
import com.gxp.meinews.mvp.presenter.FuckGoodsPresenter
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class AndroidFragment : BaseFragment<FuckGoodsPresenter>() {

    @Inject protected lateinit var mPresenter: FuckGoodsPresenter

    override fun setData(results: List<GankGoods>) {
//        mPresenter.getData()
    }

    override fun setComponent() {
        MeiApp.instance.getAppComponent().plus(GankGoodsModule(this)).inject(this)
    }

    override fun initView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =inflater!!.inflate(R.layout.activity_main,container,false)

    companion object {
        val ANDROID = "android"
        fun newInstanct():AndroidFragment{
            var fragment = AndroidFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unSubscribe()
    }
}