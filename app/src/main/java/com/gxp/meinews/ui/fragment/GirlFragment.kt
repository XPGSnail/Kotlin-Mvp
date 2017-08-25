package com.gxp.meinews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gxp.meinews.MeiApp
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.di.module.GankGoodsModule
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.presenter.GankGoodsPresenter
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class GirlFragment : BaseFragment<GankGoodsPresenter>(), BaseContract.IView {

    @Inject protected lateinit var mPresenter: GankGoodsPresenter

    override fun setData(results: List<GankGoods>) {
//        mPresenter.getData()
    }

    override fun setComponent() {
        MeiApp.instance.getAppComponent().plus(GankGoodsModule(this)).inject(this)
    }

    override fun initView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =inflater!!.inflate(R.layout.activity_main,container,false)

    companion object {
        val GIRL = "girl"
        fun newInstanct(): GirlFragment {
            var fragment = GirlFragment()
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