package com.yasin.licious.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yasin.licious.data.model.FavoritesScreenResponse
import com.yasin.licious.data.model.ResponseProduct
import com.yasin.licious.data.model.UiProduct
import com.yasin.licious.network.ViewState
import javax.inject.Inject

/**
 * Created by Yasin on 27/4/20.
 */
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val forceRefresh: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _favourites: MutableLiveData<ViewState<List<UiProduct>>> = MutableLiveData()
    val favorites: LiveData<ViewState<List<UiProduct>>> = Transformations.map(
        favoritesRepository.getFavoritesScreenResponse()
    ) {
        manageFavoritesList(it)
    }

    private fun manageFavoritesList(it: ViewState<FavoritesScreenResponse>): ViewState<List<UiProduct>> {
        when (it) {
            is ViewState.Success -> {
                val productsList: MutableList<UiProduct> = mutableListOf()
                it.data.favoritesData?.responseProducts?.forEach {
                    productsList.add(it.convertToUiProduct())
                }
                return ViewState.Success(productsList)
            }

            is ViewState.Error -> {
                return ViewState.Error(it.message)
            }
            is ViewState.Loading -> {
                return ViewState.Loading
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