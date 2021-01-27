package io.github.pengdst.githubpage.datas.repositories

import io.github.pengdst.githubpage.datas.utils.mapper.toDomain
import io.github.pengdst.githubpage.datas.utils.mapper.toDomainList
import io.github.pengdst.githubpage.datas.network.retrofit.routes.UserRoute
import retrofit2.Response
import javax.inject.Inject

class FollowingRepository @Inject constructor(
    private val route: UserRoute
) : BaseRepository() {

    suspend fun getUser(username: String) = route.getUser(username).let {
        Response.success(it.body()?.toDomain())
    }

    suspend fun getFollowing(username: String) = route.getFollowing(username).let {
        Response.success(it.body()?.toDomainList())
    }

    suspend fun getFollowers(username: String) = route.getFollowers(username).let {
        Response.success(it.body()?.toDomainList())
    }

}