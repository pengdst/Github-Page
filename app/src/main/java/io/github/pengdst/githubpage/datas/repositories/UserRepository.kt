package io.github.pengdst.githubpage.datas.repositories

import io.github.pengdst.githubpage.datas.domain.utils.toDomain
import io.github.pengdst.githubpage.datas.domain.utils.toDomainList
import io.github.pengdst.githubpage.datas.network.retrofit.responses.GenericItemResponse
import io.github.pengdst.githubpage.datas.network.retrofit.routes.UserRoute
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val route: UserRoute
): BaseRepository() {

    suspend fun getUsers() = apiRequest {
        route.getAllUser()
    }?.toDomainList()

    suspend fun getUserDetail(username: String) = route.getUser(username).let {
        Response.success(it.body()?.toDomain())
    }

    suspend fun searchUser(username: String) = apiRequest {
        route.searchUser(username)
    }?.let {
        GenericItemResponse(
            totalCount = it.totalCount,
            incompleteResults = it.incompleteResults,
            item = it.item?.toDomainList()
        )
    }

}
