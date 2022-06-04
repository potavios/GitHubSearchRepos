package dev.pauloos.core.data.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<ItemsResponse>
)
