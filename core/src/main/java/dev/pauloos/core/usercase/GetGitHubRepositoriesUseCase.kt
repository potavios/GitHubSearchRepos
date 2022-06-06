package dev.pauloos.core.usercase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.pauloos.core.data.repository.GitHubRepositoriesRepository
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.core.usercase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGitHubRepositoriesUseCase @Inject constructor (
    private val gitHubRepositoriesRepository: GitHubRepositoriesRepository
) : PagingUseCase<GetGitHubRepositoriesUseCase.GetGitHubRepositoriesParams, GitHubRepository>()
{
    override fun createFlowObservable(
        params: GetGitHubRepositoriesParams): Flow<PagingData<GitHubRepository>>
    {
        return Pager(config = params.pagingConfig) {
            gitHubRepositoriesRepository.getGitHubRepositories(params.token, params.query)
        }.flow
    }

    data class GetGitHubRepositoriesParams(
        val token: String,
        val query: String,
        val pagingConfig: PagingConfig)


}