package io.github.pengdst.githubpage.datas.network.retrofit.responses

import com.google.gson.annotations.SerializedName

data class GenericItemResponse<T>(
    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val item: T? = null
)