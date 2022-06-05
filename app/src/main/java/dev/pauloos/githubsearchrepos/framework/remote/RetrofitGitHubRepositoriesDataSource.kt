package dev.pauloos.githubsearchrepos.framework.remote

import dev.pauloos.core.data.repository.GitHubRepositoriesRemoteDataSource
import dev.pauloos.githubsearchrepos.framework.network.GitHubApi
import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitGitHubRepositoriesDataSource @Inject constructor(
    private val gitHubApi: GitHubApi
    ) : GitHubRepositoriesRemoteDataSource<DataWrapperResponse>
{
    override suspend fun fetchGitHubRepositories(token: String,
                                                 queries: Map<String, String>): DataWrapperResponse
    {
        return gitHubApi.getRepositories(token, queries)
    }
}