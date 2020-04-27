package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class ProductInventory(
    @SerializedName("rm_buffer")
    val rmBuffer: Int = 0,
    @SerializedName("hub_id")
    val hubId: Int = 0,
    @SerializedName("merchant_delta")
    val merchantDelta: Int = 0,
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("dispatched_quantity")
    val dispatchedQuantity: Int = 0,
    @SerializedName("product_id")
    val productId: String = "",
    @SerializedName("cat_id")
    val catId: Int = 0,
    @SerializedName("stock_units")
    val stockUnits: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("stock_lock")
    val stockLock: Int = 0
)