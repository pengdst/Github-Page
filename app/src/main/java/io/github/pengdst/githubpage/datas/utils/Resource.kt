package io.github.pengdst.githubpage.datas.utils


/**
 *
 * Created by Pengkuh Dwi Septiandi on 1/25/21.
 *
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengkuh-dst/
 *
 */
sealed class Resource<T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val errorMessage: String, val data: T?): Resource<T>()
    data class Loading<T>(val data: T?): Resource<T>()

    companion object {
        fun <T> success(data: T) = Success(data = data)
        fun <T> error(errorMessage: String, data: T?) = Error(errorMessage, data)
        fun <T> loading(data: T?) = Loading(data)
    }
}