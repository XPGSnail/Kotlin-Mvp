package com.gxp.meinews.api

import com.gxp.meinews.base.GankGoods
import com.gxp.meinews.base.HttpResult
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by pandaGuo on 2017/8/23.
 */

interface GankApi {

    /**
     * Android所有数据
     */
    @GET("data/Android/10/{page}")
    fun getAndroidData(@Path("page") page:Int): Observable<HttpResult<List<GankGoods>>>

    /**
     * iOS所有数据
     */
    @GET("data/iOS/10/{page}")
    fun getiOSData(@Path("page") page:Int):Observable<HttpResult<List<GankGoods>>>

    /**
     * iOS所有数据
     */
    @GET("data/福利/10/{page}")
    fun getGirlData(@Path("page") page:Int): Observable<HttpResult<List<GankGoods>>>


    /**
     * 手气不错
     */

    @GET("random/data/{type}/1")
    fun getRandom(@Path("type") type:String): Observable<HttpResult<List<GankGoods>>>
}