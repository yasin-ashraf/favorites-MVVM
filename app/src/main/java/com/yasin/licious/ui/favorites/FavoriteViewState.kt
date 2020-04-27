package com.yasin.licious.ui.favorites

import com.yasin.licious.data.model.Filter
import com.yasin.licious.data.model.UiProduct

/**
 * Created by Yasin on 28/4/20.
 */
sealed class FavoriteViewState {
    object Loading : FavoriteViewState()
    data class Success(
        val filters: List<Filter>,
        val favorites: List<UiProduct>,
        val infoMessage: String,
        val badge: String,
        val screenTitle: String
    ) : FavoriteViewState()

    data class Error(
        val ErrorMsg: String
    ) : FavoriteViewState()
}