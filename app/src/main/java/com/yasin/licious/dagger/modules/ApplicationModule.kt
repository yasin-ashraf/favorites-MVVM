package com.yasin.licious.dagger.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yasin.licious.dagger.AppScope
import com.yasin.licious.network.LiciousServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Yasin on 27/4/20.
 */
@Module
class ApplicationModule {

    @Provides
    @AppScope
    fun familyBookServices(retrofit: Retrofit): LiciousServices {
        return retrofit.create<LiciousServices>(LiciousServices::class.java)
    }

    @Provides
    @AppScope
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
        return gsonBuilder.create()
    }
}