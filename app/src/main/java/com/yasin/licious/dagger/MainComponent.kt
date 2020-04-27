package com.yasin.licious.dagger

import android.content.Context
import com.yasin.licious.dagger.modules.ApplicationModule
import com.yasin.licious.dagger.modules.ContextModule
import com.yasin.licious.dagger.modules.NetworkModule
import com.yasin.licious.dagger.modules.RetrofitModule
import com.yasin.licious.ui.favorites.FavouritesScreen
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Yasin on 27/4/20.
 * Dagger Component for app module scope
 */
@AppScope
@Component(modules = [ApplicationModule::class, ContextModule::class, NetworkModule::class, RetrofitModule::class])
interface MainComponent {
    fun injectFavoritesScreen(favouritesScreen: FavouritesScreen)

    @Component.Builder
    interface Builder {
        fun build() : MainComponent
        @BindsInstance fun context(@ApplicationContext context: Context) : Builder
    }
}