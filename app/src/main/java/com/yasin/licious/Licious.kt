package com.yasin.licious

import android.app.Application
import android.content.Context
import com.yasin.licious.dagger.DaggerMainComponent
import com.yasin.licious.dagger.MainComponent

/**
 * Created by Yasin on 27/4/20.
 */
class Licious : Application() {

    private val appComponent : MainComponent by lazy {
        DaggerMainComponent
            .builder()
            .context(this.applicationContext)
            .build()
    }

    fun Context.getAppComponent() : MainComponent = appComponent
}