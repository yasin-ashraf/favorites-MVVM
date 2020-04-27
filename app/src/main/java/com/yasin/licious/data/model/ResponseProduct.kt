package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("product_master")
    val productMaster: ProductMaster,
    @SerializedName("product_pricing")
    val productPricing: ProductPricing,
    @SerializedName("product_merchantdising")
    val productMerchantdising: ProductMerchantdising,
    @SerializedName("product_inventory")
    val productInventory: ProductInventory
)