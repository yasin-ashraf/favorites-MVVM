package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class ProductMaster(
    @SerializedName("pr_weight")
    val prWeight: String = "",
    @SerializedName("gross")
    val gross: String = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("pieces")
    val pieces: String = "",
    @SerializedName("uom")
    val uom: String = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("product_id")
    val productId: String = "",
    @SerializedName("pr_name")
    val prName: String = "",
    @SerializedName("text")
    val text: String = "",
    @SerializedName("net")
    val net: String = "",
    @SerializedName("hsn_no")
    val hsnNo: Int = 0,
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("status")
    val status: Int = 0
)