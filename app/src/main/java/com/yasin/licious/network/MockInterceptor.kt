package com.yasin.licious.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException


/**
 * Created by Yasin on 27/4/20.
 */
class MockInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
        when (url.encodedPath) {
             "/favorites" -> {
                 val response: String = ""
                 return Response.Builder()
                     .code(200)
                     .message(response)
                     .request(chain.request())
                     .protocol(Protocol.HTTP_1_1)
                     .body(response.toResponseBody("application/json".toMediaTypeOrNull()))
                     .addHeader("content-type", "application/json")
                     .build()
             }

            else -> error("invalid url path")
         }
    }
}