package io.github.pengdst.githubpage.datas.network.retrofit

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpBuilder {

    private var instance: OkHttpClient? = null

    fun okHTTPClient(): OkHttpClient {
        return instance ?: OkHttpClient
            .Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .build().also {
                instance = it
            }
    }
}