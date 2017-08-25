package com.gxp.meinews.di.component

import com.gxp.meinews.MeiApp
import com.gxp.meinews.di.module.ApiModule
import com.gxp.meinews.di.module.GankGoodsModule
import dagger.Component
import javax.inject.Singleton



/**
 * Created by pandaGuo on 2017/8/23.
 */
@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun inject(app:MeiApp)

    fun plus(module: GankGoodsModule): GankGoodsComponent

}