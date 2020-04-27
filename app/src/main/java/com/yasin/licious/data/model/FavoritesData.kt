package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class FavoritesData(
    @SerializedName("info_badge")
    val infoBadge: String = "",
    @SerializedName("filters")
    val filters: List<Filter>?,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("info_message")
    val infoMessage: String = "",
    @SerializedName("products")
    val responseProducts: List<ResponseProduct>?
)