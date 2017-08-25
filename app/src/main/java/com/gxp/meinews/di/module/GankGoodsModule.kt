package com.gxp.meinews.di.module

import com.gxp.meinews.mvp.contract.BaseContract
import dagger.Module
import dagger.Provides

/**
 * Created by pandaGuo on 2017/8/23.
 */
@Module
class GankGoodsModule(private val mView: BaseContract.IView){
    @Provides
    fun getView() = mView

//    @Provides
//    fun provideModel(mModel: BaseModel) = mModel
}