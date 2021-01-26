package io.github.pengdst.githubpage.datas.utils
//
//import androidx.annotation.MainThread
//import androidx.annotation.WorkerThread
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MediatorLiveData
//import io.github.pengdst.githubpage.datas.network.retrofit.responses.GenericItemResponse
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//
///**
// *
// * Created by Pengkuh Dwi Septiandi on 1/25/21.
// *
// * Github    : https://github.com/pengdst
// * Gitlab    : https://gitlab.com/pengdst
// * LinkedIn  : https://linkedin.com/in/pengkuh-dst/
// *
// */
//abstract class NetworkBoundResource<RequestType, ResultType> constructor(private val coroutineScope: CoroutineScope) {
//
//    private val result = MediatorLiveData<Resource<ResultType>>()
//
//    init {
//        result.value = Resource.loading(null)
//        @Suppress("LeakingThis")
//        val dbSource: LiveData<ResultType> = loadFromDb()
//        result.addSource(dbSource) { data ->
//            result.removeSource(dbSource)
//            if (shouldFetch(data)) {
//                fetchFromNetwork(dbSource)
//            } else {
//                result.addSource(dbSource) { newData ->
//                    setValue(Resource.success(newData))
//                }
//            }
//        }
//    }
//
//    @MainThread
//    private fun setValue(newValue: Resource<ResultType>) {
//        if (result.value != newValue) {
//            result.value = newValue
//        }
//    }
//
//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//        coroutineScope.launch {
//            val apiResponse = createCall()
//            // we re-attach dbSource as a new source, it will dispatch its latest value quickly
//            result.addSource(dbSource) { newData ->
//                setValue(Resource.loading(newData))
//            }
//            result.addSource(apiResponse) { response ->
//                result.removeSource(apiResponse)
//                result.removeSource(dbSource)
//                when (response) {
//                    is GenericItemResponse.SuccessResponse -> {
//                        coroutineScope.launch(Dispatchers.IO) {
//                            saveCallResult(processResponse(response))
//                            withContext(Dispatchers.Main) {
//                                // we specially request a new live data,
//                                // otherwise we will get immediately last cached value,
//                                // which may not be updated with latest results received from network.
//                                result.addSource(loadFromDb()) { newData ->
//                                    setValue(Resource.success(newData))
//                                }
//                            }
//                        }
//                    }
//                    is GenericItemResponse.EmptyResponse -> {
//                        coroutineScope.launch(Dispatchers.Main) {
//                            // reload from disk whatever we had
//                            result.addSource(loadFromDb()) { newData ->
//                                setValue(Resource.success(newData))
//                            }
//                        }
//                    }
//                    is GenericItemResponse.ErrorResponse -> {
//                        onFetchFailed()
//                        result.addSource(dbSource) { newData ->
//                            setValue(Resource.error(response.errorMessage, newData))
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    protected open fun onFetchFailed() {}
//
//    fun asLiveData() = result as LiveData<Resource<ResultType>>
//
//    @WorkerThread
//    protected open fun processResponse(response: GenericItemResponse.SuccessResponse<RequestType>) = response.body
//
//    @WorkerThread
//    protected abstract fun saveCallResult(item: RequestType)
//
//    @MainThread
//    protected abstract fun shouldFetch(data: ResultType?): Boolean
//
//    @MainThread
//    protected abstract fun loadFromDb(): LiveData<ResultType>
//
//    @MainThread
//    protected abstract suspend fun createCall(): LiveData<GenericItemResponse<RequestType>>
//}