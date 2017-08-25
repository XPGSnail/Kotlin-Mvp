package com.gxp.meinews

import android.app.Application
import com.gxp.meinews.di.component.ApiComponent
import com.gxp.meinews.di.component.DaggerApiComponent
import com.gxp.meinews.di.module.ApiModule
import com.gxp.meinews.di.module.AppModule
import com.gxp.meinews.rounter.GankClientUri
import javax.inject.Inject

/**
 * Created by pandaGuo on 2017/8/23.
 */
class MeiApp: Application() {

    init {
        instance = this
    }

    private lateinit var apiComponent:ApiComponent
    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .appModule(AppModule(this))
                .build()
    }
    fun getAppComponent() = apiComponent

    companion object {
        lateinit var instance:MeiApp
    }
}