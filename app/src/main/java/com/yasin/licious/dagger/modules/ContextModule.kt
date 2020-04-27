package com.yasin.licious.dagger.modules

import android.content.Context
import com.yasin.licious.dagger.AppScope
import com.yasin.licious.dagger.ApplicationContext
import dagger.Binds
import dagger.Module

/**
 * Created by Yasin on 27/4/20.
 */
@Module
abstract class ContextModule {

    @AppScope
    @Binds
    abstract fun provideContext(@ApplicationContext context: Context) : Context

}