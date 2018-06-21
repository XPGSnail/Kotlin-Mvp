package com.gxp.meinews.mvp.presenter

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by pandaGuo on 2017/8/23.
 */
open class BasePresenter{

    var compositeSubscription = CompositeSubscription()

    protected fun addSubscription(subscription: Subscription) {
        compositeSubscription.add(subscription)
    }

    fun unSubscribe() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe()
        }
    }
}