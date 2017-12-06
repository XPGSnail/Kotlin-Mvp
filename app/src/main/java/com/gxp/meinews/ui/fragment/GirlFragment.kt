package com.gxp.meinews.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gxp.meinews.MeiApp
import com.gxp.meinews.R
import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.di.module.GankGoodsModule
import com.gxp.meinews.imageload.ImageloadUtils
import com.gxp.meinews.mvp.contract.BaseContract
import com.gxp.meinews.mvp.presenter.GankGoodsPresenter
import com.gxp.meinews.rounter.GankClientUri
import com.gxp.meinews.ui.adapter.MeiZiAdapter
import com.gxp.meinews.ui.widget.DividerDecoration
import kotlinx.android.synthetic.main.fragment_meizi.*

/**
 * Created by pandaGuo on 2017/8/23.
 */
class GirlFragment : BaseFragment<GankGoodsPresenter>(), BaseContract.IView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        Snackbar.make(view!!, "hi,what are you wishing for？", Snackbar.LENGTH_SHORT)
                .setAction("1", null).show()
    }

    override fun onRefresh() {
        mPage = 1
        mPresenter.getData(mPage, GIRL, GankClientUri.FLAG_REFRESH)
    }

    override fun onLoadMoreRequested() {
        recyclerView.postDelayed({
            if (mPage >= MAX_PAGE) {
                mAdapter.loadMoreEnd()
            } else {
                ++mPage
                mPresenter.getData(mPage, GIRL, GankClientUri.FLAG_LOAD_MORE)
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

//    @Inject protected lateinit var mPresenter: GankGoodsPresenter
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
            = inflater!!.inflate(R.layout.fragment_meizi, container, false)

    override fun initData() {
        initRecyclerView()
        mPresenter.getData(mPage, GIRL, GankClientUri.FLAG_REFRESH)
        initListener()
    }

    private fun initListener() {
        swipeRefreshLayout.setOnRefreshListener(this)
        mAdapter.setOnLoadMoreListener(this,recyclerView)
        mAdapter.onItemClickListener = this
    }

    private var mList = ArrayList<GankGoods>()
    private lateinit var mAdapter: MeiZiAdapter

    private fun initRecyclerView() {

        swipeRefreshLayout.setColorSchemeResources(R.color.pink, R.color.blue_grey)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
        (recyclerView.layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        recyclerView.addItemDecoration(DividerDecoration(10))
        mAdapter = MeiZiAdapter(mList)
        recyclerView.adapter = mAdapter
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> ImageloadUtils.pause()
                    else -> ImageloadUtils.resume()
                }
            }
        })
    }

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