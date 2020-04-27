package com.yasin.licious.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yasin.licious.data.model.FavoritesScreenResponse
import com.yasin.licious.data.model.ResponseProduct
import com.yasin.licious.data.model.UiProduct
import com.yasin.licious.network.NetworkState
import javax.inject.Inject

/**
 * Created by Yasin on 27/4/20.
 */
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favoritesViewState: LiveData<FavoriteViewState> = Transformations.map(
        favoritesRepository.getFavoritesScreenResponse()
    ) {
        composeViewState(it)
    }

    private fun composeViewState(it: NetworkState<FavoritesScreenResponse>): FavoriteViewState {
        when (it) {
            is NetworkState.Success -> {
                val allFavorites: MutableList<UiProduct> = mutableListOf()
                it.data.favoritesData?.responseProducts?.forEach {
                    allFavorites.add(it.convertToUiProduct())
                }
                return FavoriteViewState.Success(
                    filters = it.data.favoritesData?.filters ?: listOf(),
                    favorites = allFavorites,
                    badge = it.data.favoritesData?.infoBadge ?: "Items",
                    infoMessage = it.data.favoritesData?.infoMessage ?: "Your favorite Items",
                    screenTitle = it.data.favoritesData?.title ?: "Favorite List"
                )
            }

            is NetworkState.Error -> {
                return FavoriteViewState.Error(it.message)
            }
            is NetworkState.Loading -> {
                return FavoriteViewState.Loading
            }
        }
    }

    private fun ResponseProduct.convertToUiProduct(): UiProduct {
        return UiProduct(
            productId = this.productMaster.productId,
            productName = this.productMaster.prName,
            uom = this.productMaster.uom,
            actualPrice = this.productPricing.basePrice,
            offerPrice = 0.0,
            image = this.productMerchantdising.prImage,
            deliveryType = this.productMerchantdising.productDeliveryType,
            netWt = this.productMaster.net
        )
    }
}