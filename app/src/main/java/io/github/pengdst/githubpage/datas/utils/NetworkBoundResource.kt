package io.github.pengdst.githubpage.datas.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.switchMap
import retrofit2.Response

abstract class NetworkBoundResource<NetworkType, LocalType, DomainType> {

    private val result = MediatorLiveData<Resource<DomainType>>()

    init {
        result.postValue(Resource.loading())

        @Suppress("LeakingThis")
        val dbSource = loadDB()

        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch()) {
                fetchFromNetwork(
                    dbSource.switchMap {
                        processLocalLiveData(it)
                    }
                )
            } else {
                result.addSource(dbSource) {
                    setValue(Resource.success(processLocalResponse(it)))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<DomainType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    @Suppress("LeakingThis")
    fun fetchFromNetwork(dbSource: LiveData<DomainType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) {
            setValue(Resource.loading(it))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(dbSource)
            result.removeSource(apiResponse)

            if (response.isSuccessful) {
                saveCallResult(processResponse(response))
                result.addSource(loadDB()) { data ->
                    setValue(Resource.success(processLocalResponse(data)))
                }
            } else {
                onFetchFailed()
                result.addSource(dbSource) {
                    setValue(Resource.error(response.errorBody()?.string().toString(), it))
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<DomainType>>

    abstract fun processLocalLiveData(it: LocalType): LiveData<DomainType>
    abstract fun processLocalResponse(dbSource: LocalType): DomainType
    abstract fun processResponse(response: Response<NetworkType>): LocalType
    abstract fun createCall(): LiveData<Response<NetworkType>>
    abstract fun saveCallResult(body: LocalType)
    abstract fun shouldFetch(): Boolean
    abstract fun loadDB(): LiveData<LocalType>
    abstract fun onFetchFailed()

}