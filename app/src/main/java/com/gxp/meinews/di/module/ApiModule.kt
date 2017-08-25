package com.gxp.meinews.di.module

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gxp.meinews.api.GankApi
import com.gxp.meinews.rounter.GankClientUri
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import java.io.File

/**
 * Created by pandaGuo on 2017/8/23.
 */
@Module(includes = arrayOf(AppModule::class))
class ApiModule() {
    @Provides
    fun provideRetrofit(client:OkHttpClient,gson:Gson)=
            Retrofit.Builder()
                    .baseUrl(GankClientUri.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()

    @Provides
    fun provideGson() = GsonBuilder().create()

//    @Provides
//    fun provideHttpUrl() = HttpUrl.parse(httpUrl)

    @Provides
    fun provideOkhttpClient(context: Context, interceptor: HttpLoggingInterceptor):OkHttpClient{
        val cacheSize = 1024 * 1024 * 10L
        val cacheDir = File(context.cacheDir, "okhttp")
        val cache = Cache(cacheDir, cacheSize)
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor).build()
    }

    @Provides
    fun provideInterceptor() :HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor{
            msg -> Log.d("okhttp",msg)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideApi(retrofit:Retrofit) = retrofit.create(GankApi::class.java)
}