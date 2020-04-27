package com.yasin.licious.dagger.modules

import com.google.gson.Gson
import com.yasin.licious.dagger.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Yasin on 10/4/20.
 */
@Module(includes = [ApplicationModule::class])
open class RetrofitModule {

    @Provides
    @AppScope
    open fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        /* INFO: BASE URL FOR APP*/
        val baseUrl = "http://localhost:8080"

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }
}