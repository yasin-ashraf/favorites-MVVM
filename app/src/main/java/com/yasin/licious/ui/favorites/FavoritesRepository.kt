package com.yasin.licious.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yasin.licious.data.model.FavoritesScreenResponse
import com.yasin.licious.network.LiciousServices
import com.yasin.licious.network.ViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Yasin on 27/4/20.
 */
class FavoritesRepository @Inject constructor(
    private val liciousServices: LiciousServices
) {

    fun getFavoritesScreenResponse(): LiveData<ViewState<FavoritesScreenResponse>> {
        val favoriteScreenResponse: MutableLiveData<ViewState<FavoritesScreenResponse>> =
            MutableLiveData()
        liciousServices.getCurrent().enqueue(object : Callback<FavoritesScreenResponse> {
            override fun onResponse(
                call: Call<FavoritesScreenResponse>,
                response: Response<FavoritesScreenResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    favoriteScreenResponse.value =
                        ViewState.Success(response.body() ?: FavoritesScreenResponse(null))
                }else {
                    favoriteScreenResponse.value =
                        ViewState.Error("Unknown error from Server!")
                }
            }

            override fun onFailure(call: Call<FavoritesScreenResponse>, t: Throwable) {
                favoriteScreenResponse.value =
                    ViewState.Error("Network error!")
            }
        })
        return favoriteScreenResponse
    }
}