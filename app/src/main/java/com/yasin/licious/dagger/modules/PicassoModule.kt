package com.yasin.licious.dagger.modules

import android.content.Context

import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.yasin.licious.dagger.AppScope
import com.yasin.licious.dagger.ApplicationContext

import dagger.Module
import dagger.Provides

/**
 * Created by Yasin on 2/10/19.
 */
@Module(includes = [ContextModule::class, NetworkModule::class])
class PicassoModule {

    @Provides
    @AppScope
    fun picasso(@ApplicationContext context: Context): Picasso {
        return Picasso.Builder(context)
            .loggingEnabled(true)
            .downloader(OkHttp3Downloader(context))
            .build()
    }

}
