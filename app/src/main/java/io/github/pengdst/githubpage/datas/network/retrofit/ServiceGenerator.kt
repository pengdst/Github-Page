package io.github.pengdst.githubpage.datas.network.retrofit

import io.github.pengdst.githubpage.datas.network.retrofit.routes.UserRoute

object ServiceGenerator {
    fun createUserRoute() = RetrofitBuilder.retrofit(OkHttpBuilder.okHTTPClient())
        .create(UserRoute::class.java)
}