package dev.pauloos.githubsearchrepos.framework.network.response

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String
)