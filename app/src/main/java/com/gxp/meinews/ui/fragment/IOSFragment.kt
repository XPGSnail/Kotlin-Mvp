package com.gxp.meinews.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gxp.meinews.MeiApp
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.di.module.GankGoodsModule
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.presenter.GankGoodsPresenter
import com.gxp.meinews.rounter.GankClientUri
import com.gxp.meinews.ui.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_ios.*
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class IOSFragment : BaseFragment<GankGoodsPresenter>(), BaseContract.IView {

    override fun showLoading() {
    }

    override fun showLoadingMore() {
    }

    override fun addData(result: List<GankGoods>) {
    }

    override fun hideLoadingMore(b: Boolean) {
    }

    override fun hideLoading() {
    }

    @Inject protected lateinit var mPresenter: GankGoodsPresenter
    private var mPage = 1
    private val MAX_PAGE = 10


    override fun setData(results: List<GankGoods>) {
//        mPresenter.getData()
        mAdapter!!.addDatas(results)
    }

    override fun setComponent() {
        MeiApp.instance.getAppComponent().plus(GankGoodsModule(this)).inject(this)
    }

    override fun inflateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View
            =inflater!!.inflate(R.layout.fragment_ios,container,false)

    override fun initData() {
        initRecyclerView()
        mPresenter.getData(mPage, AndroidFragment.ANDROID, GankClientUri.FLAG_REFRESH)
    }

    private var mList = ArrayList<GankGoods>()
    private var mAdapter: BaseAdapter<GankGoods>? = null

    private fun initRecyclerView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.pink, R.color.blue_grey)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
//        recyclerView.addItemDecoration(DividerDecoration(10))
        mAdapter = BaseAdapter(mList)
        recyclerView.adapter = mAdapter
    }

    companion object {
        val IOS = "ios"
        fun newInstanct(): IOSFragment {
            var fragment = IOSFragment()
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