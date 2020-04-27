package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class FavoritesScreen(
    @SerializedName("data")
    val favoritesData: FavoritesData,
    @SerializedName("statusMessage")
    val statusMessage: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)