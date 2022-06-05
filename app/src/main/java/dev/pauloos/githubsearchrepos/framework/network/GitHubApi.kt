package dev.pauloos.githubsearchrepos.framework.network

import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface GitHubApi {

    @GET("repositories")
    suspend fun getRepositories(
        @Header("Authorization") token: String,
        @QueryMap queries: Map<String, String>
    ): DataWrapperResponse

}