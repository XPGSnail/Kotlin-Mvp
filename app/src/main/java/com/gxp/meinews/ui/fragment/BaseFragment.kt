package com.gxp.meinews.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.presenter.IPresenter
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
abstract class BaseFragment<P: IPresenter> : Fragment(),BaseContract.IView {

    @Inject
    protected lateinit var mPresenter:P

    private lateinit var mInitView:View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mInitView = inflateView(inflater, container, savedInstanceState)
        setComponent()
        Log.d("BaseFragment","onCreateView")
        return mInitView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("BaseFragment","onActivityCreated")
        initData()
    }

    abstract fun initData()

    abstract fun setComponent()

    abstract fun inflateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View



}