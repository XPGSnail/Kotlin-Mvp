package com.gxp.meinews.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gxp.meinews.MeiApp
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.di.module.GankGoodsModule
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.presenter.GankGoodsPresenter
import com.gxp.meinews.rounter.GankClientUri
import com.gxp.meinews.ui.adapter.IOSAdapter
import com.gxp.meinews.ui.widget.DividerDecoration
import kotlinx.android.synthetic.main.fragment_ios.*
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class IOSFragment : BaseFragment<GankGoodsPresenter>(), BaseContract.IView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    override fun getContext(): Context {
        return context
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        Snackbar.make(view!!, "hi,what are you wishing for？", Snackbar.LENGTH_SHORT)
                .setAction("1", null).show()
    }

    override fun onRefresh() {
        mPage = 1
        mPresenter.getData(mPage, IOS, GankClientUri.FLAG_REFRESH)
    }

    override fun onLoadMoreRequested() {
        recyclerView.postDelayed({
            if (mPage >= MAX_PAGE) {
                mAdapter.loadMoreEnd()
            } else {
                ++mPage
                mPresenter.getData(mPage, IOS, GankClientUri.FLAG_LOAD_MORE)
            }
        }, 500)
    }


    override fun showLoadingMore() {
        swipeRefreshLayout.isEnabled = false
    }

    override fun addData(result: List<GankGoods>) {
        mAdapter.addData(result)
    }

    override fun hideLoadingMore(b: Boolean) {
        if (b) {
            mAdapter.loadMoreComplete()
        } else {
            mAdapter.loadMoreFail()
        }
        swipeRefreshLayout.isEnabled = true
    }

    override fun hideLoading() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
        mAdapter.setEnableLoadMore(true)
    }

    override fun showLoading() {
        mAdapter.setEnableLoadMore(false)
        swipeRefreshLayout.isEnabled = true
    }
    @Inject protected lateinit var mPresenter: GankGoodsPresenter
    private var mPage = 1
    private val MAX_PAGE = 10


    override fun setData(results: List<GankGoods>) {
        mAdapter.setNewData(results)
        mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
    }

    override fun setComponent() {
        MeiApp.instance.getAppComponent().plus(GankGoodsModule(this)).inject(this)
    }

    override fun inflateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater!!.inflate(R.layout.fragment_ios, container, false)

    override fun initData() {
        initRecyclerView()
        mPresenter.getData(mPage, IOS, GankClientUri.FLAG_REFRESH)
        initListener()
    }

    private fun initListener() {
        swipeRefreshLayout.setOnRefreshListener(this)
        mAdapter.setOnLoadMoreListener(this)
        mAdapter.onItemClickListener = this
    }

    private var mList = ArrayList<GankGoods>()
    private lateinit var mAdapter: IOSAdapter

    private fun initRecyclerView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.pink, R.color.blue_grey)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerDecoration(10))
        mAdapter = IOSAdapter(mList)
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