package dev.pauloos.githubsearchrepos.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<ItemsResponse>
)