package com.gxp.meinews.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.presenter.GankGoodsPresenter

/**
 * Created by pandaGuo on 2017/8/23.
 */
abstract class BaseFragment<P: GankGoodsPresenter>:Fragment(),BaseContract.IView {


    private lateinit var mInitView:View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mInitView = initView(inflater, container, savedInstanceState)
        setComponent()
        return mInitView
    }

    abstract fun setComponent()

    abstract fun initView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View



}