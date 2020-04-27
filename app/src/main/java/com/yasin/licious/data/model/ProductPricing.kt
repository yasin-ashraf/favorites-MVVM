package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class ProductPricing(
    @SerializedName("sgst")
    val sgst: Double = 0.0,
    @SerializedName("unit_gram")
    val unitGram: Int = 0,
    @SerializedName("hub_id")
    val hubId: Int = 0,
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("product_id")
    val productId: String = "",
    @SerializedName("base_price")
    val basePrice: Double = 0.0,
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("cgst")
    val cgst: Double = 0.0,
    @SerializedName("price_gram")
    val priceGram: Double = 0.0,
    @SerializedName("city_id")
    val cityId: Int = 0
)