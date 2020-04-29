package com.yasin.licious.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yasin.licious.data.model.FavoritesScreenResponse
import com.yasin.licious.data.model.ResponseProduct
import com.yasin.licious.data.model.UiProduct
import com.yasin.licious.data.utils.FILTER_ALL
import com.yasin.licious.data.utils.FILTER_EXPRESS
import com.yasin.licious.network.NetworkState
import javax.inject.Inject

/**
 * Created by Yasin on 27/4/20.
 */
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _currentFilter : MutableLiveData<String> = MutableLiveData()
    private val networkResponse: LiveData<NetworkState<FavoritesScreenResponse>> =
        Transformations.switchMap(_currentFilter) {
            favoritesRepository.getFavoritesScreenResponse()
        }
    val favoritesViewState: LiveData<FavoriteViewState> = Transformations.map(
        networkResponse
    ) {
        composeViewState(it)
    }

    init {
        _currentFilter.value = FILTER_ALL
    }

    private fun composeViewState(it: NetworkState<FavoritesScreenResponse>): FavoriteViewState {
        when (it) {
            is NetworkState.Success -> {
                val favoriteItems: MutableList<UiProduct> = mutableListOf()
                filterItems(it, favoriteItems)
                return FavoriteViewState.Success(
                    filters = it.data.favoritesData?.filters ?: listOf(),
                    favorites = favoriteItems,
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

    private fun filterItems(
        it: NetworkState.Success<FavoritesScreenResponse>,
        favoriteItems: MutableList<UiProduct>
    ) {
        when(_currentFilter.value) {
            FILTER_ALL -> {
                it.data.favoritesData?.responseProducts?.forEach {
                    favoriteItems.add(it.convertToUiProduct())
                }
            }
            FILTER_EXPRESS -> {
                it.data.favoritesData?.responseProducts?.forEach {
                    if(it.productMerchantdising.productDeliveryType == "Express")
                        favoriteItems.add(it.convertToUiProduct())
                }
            }
        }
    }

    fun filterFavorites(filterType: String) {
        this._currentFilter.value = filterType
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
            netWt = this.productMaster.net,
            stockUnits = productInventory.stockUnits
        )
    }
}