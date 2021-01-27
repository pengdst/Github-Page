package io.github.pengdst.githubpage.datas.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.network.retrofit.responses.listmapper.UserDetailNullableInputListMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto
import io.github.pengdst.githubpage.datas.network.retrofit.routes.UserRoute
import io.github.pengdst.githubpage.datas.utils.NetworkBoundResource
import io.github.pengdst.githubpage.datas.utils.Resource
import io.github.pengdst.githubpage.datas.utils.mapper.toDomain
import io.github.pengdst.githubpage.datas.utils.mapper.toDomainList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val route: UserRoute,
) : BaseRepository() {

    private var result = mutableListOf<UserDetail>()

    suspend fun getUsers(): LiveData<Resource<List<UserDetail>>> {
        return object :
            NetworkBoundResource<List<UserDetailDto>?, List<UserDetail>, List<UserDetail>>() {
            override fun processLocalLiveData(it: List<UserDetail>): LiveData<List<UserDetail>> {
                return liveData {
                    emit(it)
                }
            }

            override fun processLocalResponse(dbSource: List<UserDetail>) = dbSource

            override fun processResponse(response: Response<List<UserDetailDto>?>): List<UserDetail> {
                return UserDetailNullableInputListMapper.toDomainModel(response.body())
            }

            override fun createCall(): LiveData<Response<List<UserDetailDto>?>> {
                return liveData {
                    emit(
                        route.getAllUser()
                    )
                }
            }

            override fun saveCallResult(body: List<UserDetail>) {
                result = body.toMutableList()
            }

            override fun shouldFetch() = true

            override fun loadDB(): LiveData<List<UserDetail>> {
                return liveData {
                    emit(result)
                }
            }

            override fun onFetchFailed() {
            }
        }.asLiveData()
    }

    suspend fun getUserDetail(username: String) = withContext(Dispatchers.IO) {
        val response = route.getUser(username)

        return@withContext liveData {
            emit(
                if (response.isSuccessful) {
                    response.let { responseBody ->
                        responseBody.body()?.let {
                            Resource.success(it.toDomain())
                        } ?: Resource.error("Body is empty")
                    }
                } else {
                    Resource.error(response.message())
                }
            )
        }
    }

    suspend fun searchUser(username: String) = withContext(Dispatchers.IO) {
        val response = route.searchUser(username)

        return@withContext liveData {
            emit(
                if (response.isSuccessful) {
                    response.let { responseBody ->
                        responseBody.body()?.item?.let {
                            Resource.success(it.toDomainList())
                        } ?: Resource.error("Body is empty")
                    }
                } else {
                    Resource.error(response.message())
                }
            )
        }
    }

}
