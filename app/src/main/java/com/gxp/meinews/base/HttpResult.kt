package com.gxp.meinews.base

/**
 * Created by pandaGuo on 2017/8/23.
 */
data class HttpResult<T>(val error:Boolean,
        var results:T){
}