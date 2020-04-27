package com.yasin.licious

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.yasin.licious.dagger.DaggerMainComponent
import com.yasin.licious.dagger.MainComponent

/**
 * Created by Yasin on 27/4/20.
 */
class Licious : Application() {

    val appComponent : MainComponent by lazy {
        DaggerMainComponent
            .builder()
            .context(this.applicationContext)
            .build()
    }

    companion object {
        fun getApp(context: Context) : Licious {
            return context.applicationContext as Licious
        }
    }
}

fun Fragment.getAppComponent() : MainComponent = Licious.getApp(this.requireContext()).appComponent