package dev.pauloos.core.usercase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.pauloos.core.data.repository.GitHubRepositoriesRepository
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.core.usercase.GetGitHubRepositoriesUseCase.GetGitHubRepositoriesParams
import dev.pauloos.core.usercase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetGitHubRepositoriesUseCase
{
    operator fun invoke(params: GetGitHubRepositoriesParams): Flow<PagingData<GitHubRepository>>

    data class GetGitHubRepositoriesParams(
        val token: String,
        val query: String,
        val pagingConfig: PagingConfig)
}

class GetGitHubRepositoriesUseCaseImpl @Inject constructor (
    private val gitHubRepositoriesRepository: GitHubRepositoriesRepository
): PagingUseCase<GetGitHubRepositoriesParams, GitHubRepository>(),
   GetGitHubRepositoriesUseCase
{
    override fun createFlowObservable(params: GetGitHubRepositoriesParams
    ): Flow<PagingData<GitHubRepository>>
    {
        val pagingSource =
            gitHubRepositoriesRepository.getGitHubRepositories(params.token, params.query)

        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}