package io.github.pengdst.githubpage.datas.network.retrofit.routes

import io.github.pengdst.githubpage.datas.constants.ApiConstant
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto
import io.github.pengdst.githubpage.datas.network.retrofit.responses.GenericItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRoute {

    @Headers(
        "Accept: application/vnd.github.v3+json",
        "Authorization: token ${ApiConstant.GITHUB_TOKEN} "
    )
    @GET("users")
    suspend fun getAllUser(): Response<List<UserDetailDto>?>

    @Headers(
        "Accept: application/vnd.github.v3+json",
        "Authorization: token ${ApiConstant.GITHUB_TOKEN} "
    )
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<UserDetailDto?>

    @Headers(
        "Accept: application/vnd.github.v3+json",
        "Authorization: token ${ApiConstant.GITHUB_TOKEN} "
    )
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<List<UserDetailDto>?>

    @Headers(
        "Accept: application/vnd.github.v3+json",
        "Authorization: token ${ApiConstant.GITHUB_TOKEN} "
    )
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Response<List<UserDetailDto>?>

    @Headers(
        "Accept: application/vnd.github.v3+json",
        "Authorization: token ${ApiConstant.GITHUB_TOKEN} "
    )

    @GET("search/users")
    suspend fun searchUser(@Query("q") username: String): Response<GenericItemResponse<List<UserDetailDto>>>

}