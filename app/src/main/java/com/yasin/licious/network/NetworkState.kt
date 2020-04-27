package com.yasin.licious.network

/**
 * Created by Yasin on 27/4/20.
 */
sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()
    data class Error<T>(val message: String) : NetworkState<T>()
    data class Success<T>(val data: T) : NetworkState<T>()
}