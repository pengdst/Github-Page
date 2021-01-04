package io.github.pengdst.githubpage.datas.network.retrofit

import io.github.pengdst.githubpage.datas.constants.ApiConstant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private var instance: Retrofit? = null

    fun retrofit(okHTTPClient: OkHttpClient): Retrofit {

        return instance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHTTPClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().also {
                instance = it
            }
    }

}