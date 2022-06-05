package dev.pauloos.core.data.network

import dev.pauloos.core.data.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GitHubApi {

    @GET("repositories")
    suspend fun getRepositories(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse

}