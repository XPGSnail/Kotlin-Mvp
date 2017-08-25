package com.gxp.meinews.di.component

import com.gxp.meinews.di.module.FuckGoodsModule
import com.gxp.meinews.ui.fragment.AndroidFragment
import com.gxp.meinews.ui.fragment.GirlFragment
import com.gxp.meinews.ui.fragment.IOSFragment
import dagger.Subcomponent

/**
 * Created by wing on 16-11-24.
 */
@Subcomponent(modules = arrayOf(FuckGoodsModule::class))
interface FuckGoodsComponent {
    fun inject(fragment: AndroidFragment)
    fun inject(fragment: IOSFragment)
    fun inject(fragment: GirlFragment)
}
