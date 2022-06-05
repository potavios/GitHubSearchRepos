package dev.pauloos.githubsearchrepos.framework.network

import androidx.paging.PagingSource
import dev.pauloos.core.data.repository.GitHubRepositoriesRemoteDataSource
import dev.pauloos.core.data.repository.GitHubRepositoriesRepository
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class GitHubRepositoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: GitHubRepositoriesRemoteDataSource<DataWrapperResponse>
    ) : GitHubRepositoriesRepository
{
    override fun getGitHubRepositories(
        token: String,
        query: String
    ): PagingSource<Int, GitHubRepository>
    {
        return GitHubRepositoriesPaging()
    }

}