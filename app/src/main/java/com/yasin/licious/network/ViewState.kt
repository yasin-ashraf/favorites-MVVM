package com.yasin.licious.network

/**
 * Created by Yasin on 27/4/20.
 */
sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Error<T>(val message: String) : ViewState<T>()
    data class Success<T>(val data: T) : ViewState<T>()
}