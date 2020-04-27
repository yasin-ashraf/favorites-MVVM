package com.yasin.licious.data.model

import com.google.gson.annotations.SerializedName

data class ProductMerchantdising(
    @SerializedName("usp_desc")
    val uspDesc: String = "",
    @SerializedName("short_description")
    val shortDescription: String = "",
    @SerializedName("count_sort")
    val countSort: Int = 0,
    @SerializedName("pr_tags")
    val prTags: String = "",
    @SerializedName("serves")
    val serves: Int = 0,
    @SerializedName("product_delivery_type")
    val productDeliveryType: String = "",
    @SerializedName("msite_desc")
    val msiteDesc: String = "",
    @SerializedName("display_order")
    val displayOrder: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("pdp_serves_img_url")
    val pdpServesImgUrl: String = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("no_of_piceces")
    val noOfPiceces: String = "",
    @SerializedName("meta_keywords")
    val metaKeywords: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("best_before")
    val bestBefore: String = "",
    @SerializedName("slider_images")
    val sliderImages: String = "",
    @SerializedName("merchandise_name")
    val merchandiseName: String = "",
    @SerializedName("score")
    val score: Int = 0,
    @SerializedName("pdp_bestbefore_img_url")
    val pdpBestbeforeImgUrl: String = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("product_id")
    val productId: String = "",
    @SerializedName("net_wt_img_pdp")
    val netWtImgPdp: String = "",
    @SerializedName("cooking_time")
    val cookingTime: String = "",
    @SerializedName("product_shortname")
    val productShortname: String = "",
    @SerializedName("pdp_pieces_img_url")
    val pdpPiecesImgUrl: String = "",
    @SerializedName("meta_title")
    val metaTitle: String = "",
    @SerializedName("pdp_net_wt")
    val pdpNetWt: String = "",
    @SerializedName("gross_wt_img_pdp")
    val grossWtImgPdp: String = "",
    @SerializedName("meta_description")
    val metaDescription: String = "",
    @SerializedName("hub_id")
    val hubId: Int = 0,
    @SerializedName("pdp_gross_wt")
    val pdpGrossWt: String = "",
    @SerializedName("product_header_tags")
    val productHeaderTags: String = "",
    @SerializedName("pdp_cooktime_img_url")
    val pdpCooktimeImgUrl: String = "",
    @SerializedName("pr_image_bucket")
    val prImageBucket: String = "",
    @SerializedName("cut_off_time")
    val cutOffTime: Int = 0,
    @SerializedName("inv_sort")
    val invSort: Int = 0,
    @SerializedName("city_id")
    val cityId: Int = 0,
    @SerializedName("pr_image")
    val prImage: String = "",
    @SerializedName("wt_msg")
    val wtMsg: String = ""
)