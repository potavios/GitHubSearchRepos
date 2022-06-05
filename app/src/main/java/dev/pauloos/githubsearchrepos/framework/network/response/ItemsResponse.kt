package dev.pauloos.githubsearchrepos.framework.network.response

import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    val id: String,
    val name: String,
    val owner: OwnerResponse,
    @SerializedName("stargazers_count")
    val totalStars: Int,
    @SerializedName("forks_count")
    val totalForks: Int
)
