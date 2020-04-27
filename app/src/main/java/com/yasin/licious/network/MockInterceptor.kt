package com.yasin.licious.network

import android.content.Context
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by Yasin on 27/4/20.
 */
class MockInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
        when (url.encodedPath) {
             "/favorites" -> {
                 val response: String = loadJsonResponseFromAsset(context)
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

private fun loadJsonResponseFromAsset(context: Context) : String {
    var json : String? = null
    try {
        val inputStream : InputStream = context.assets.open("Response.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer)
    } catch (ioException : IOException) {
        ioException.printStackTrace()
    }
    return json ?: ""
}