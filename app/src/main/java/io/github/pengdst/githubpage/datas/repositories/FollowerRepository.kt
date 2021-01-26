package io.github.pengdst.githubpage.datas.repositories

import io.github.pengdst.githubpage.datas.domain.utils.toDomainList
import io.github.pengdst.githubpage.datas.network.retrofit.routes.UserRoute
import retrofit2.Response
import javax.inject.Inject

class FollowerRepository @Inject constructor(
    private val route: UserRoute
) : BaseRepository() {

    suspend fun getFollowers(username: String) = route.getFollowers(username).let {
        Response.success(it.body()?.toDomainList())
    }

}