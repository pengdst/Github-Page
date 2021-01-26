package io.github.pengdst.githubpage.datas.network

import io.github.pengdst.githubpage.utils.error.ApiException
import org.json.JSONException
import retrofit2.Response
import java.lang.StringBuilder

object SafeApiRequest {

    suspend fun <T> apiRequest(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        val message = StringBuilder()

        if (response.isSuccessful){
            return response.body()
        }
        else {
            val error = response.errorBody()

            error?.let {
                @Suppress("BlockingMethodInNonBlockingContext")
                response.errorBody()?.string()?.let {
                    try {
                        message.append(it)
                    }
                    catch (e: JSONException){
                        e.printStackTrace()
                    }
                    message.append("\n")
                }
            }

            message.append("Error code: ${response.code()}")

            throw ApiException(message.toString())
        }
    }

}