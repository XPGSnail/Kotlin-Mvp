package com.gxp.meinews.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by pandaGuo on 2017/8/23.
 */
@Module
class AppModule(private val context:Context) {
    @Provides fun provideContext() = context
}