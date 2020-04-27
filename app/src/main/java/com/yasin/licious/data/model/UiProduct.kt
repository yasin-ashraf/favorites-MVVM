package com.yasin.licious.data.model

/**
 * Created by Yasin on 27/4/20.
 */
data class UiProduct(
    val productId : String,
    val productName : String,
    val uom : String,
    val netWt : String,
    val actualPrice : Double,
    val offerPrice : Double,
    val image : String,
    val deliveryType : String
)