package io.github.pengdst.githubpage.datas.repositories

import io.github.pengdst.githubpage.datas.network.SafeApiRequest
import retrofit2.Response

abstract class BaseRepository {

    protected suspend fun <T> apiRequest(request: suspend ()->Response<T>) = SafeApiRequest.apiRequest(request)

}