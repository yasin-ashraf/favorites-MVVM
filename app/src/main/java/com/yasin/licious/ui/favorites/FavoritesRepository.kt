package com.yasin.licious.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yasin.licious.data.model.FavoritesScreenResponse
import com.yasin.licious.network.LiciousServices
import com.yasin.licious.network.NetworkState
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

    fun getFavoritesScreenResponse(): LiveData<NetworkState<FavoritesScreenResponse>> {
        val favoriteScreenResponse: MutableLiveData<NetworkState<FavoritesScreenResponse>> =
            MutableLiveData()
        favoriteScreenResponse.value = NetworkState.Loading
        liciousServices.getCurrent().enqueue(object : Callback<FavoritesScreenResponse> {
            override fun onResponse(
                call: Call<FavoritesScreenResponse>,
                response: Response<FavoritesScreenResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    favoriteScreenResponse.value =
                        NetworkState.Success(response.body() ?: FavoritesScreenResponse(null))
                } else {
                    Log.e("Parse Error", response.errorBody().toString())
                    favoriteScreenResponse.value =
                        NetworkState.Error("Unknown error from Server!")
                }
            }

            override fun onFailure(call: Call<FavoritesScreenResponse>, t: Throwable) {
                Log.e("Network Error", t.toString())
                favoriteScreenResponse.value =
                    NetworkState.Error("Network error!")
            }
        })
        return favoriteScreenResponse
    }
}