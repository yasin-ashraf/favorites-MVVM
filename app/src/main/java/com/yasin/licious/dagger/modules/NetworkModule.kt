package com.yasin.licious.dagger.modules

import android.content.Context
import com.yasin.licious.dagger.AppScope
import com.yasin.licious.dagger.ApplicationContext
import com.yasin.licious.network.MockInterceptor

import java.util.concurrent.TimeUnit

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Yasin on 27/4/20.
 */
@Module
class NetworkModule {

    @Provides
    @AppScope
    fun loggingInterceptor(@ApplicationContext context : Context): MockInterceptor {
        return MockInterceptor(context)
    }


    @Provides
    @AppScope
    fun okHttpClient(interceptor: MockInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }
}
