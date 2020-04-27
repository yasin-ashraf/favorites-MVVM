package com.yasin.licious.network

import com.yasin.licious.data.model.FavoritesScreenResponse
import retrofit2.http.GET

/**
 * Created by Yasin on 27/4/20.
 */
interface LiciousServices {

    @GET("/favorites")
    fun getCurrent(): FavoritesScreenResponse
}