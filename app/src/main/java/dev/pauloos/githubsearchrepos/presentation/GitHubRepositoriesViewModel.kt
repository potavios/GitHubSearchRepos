package dev.pauloos.githubsearchrepos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.core.usercase.GetGitHubRepositoriesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GitHubRepositoriesViewModel @Inject constructor(
    private val getGitHubRepositoriesUseCase: GetGitHubRepositoriesUseCase
) : ViewModel()
{
    fun gitHubRepositoriesPagingData(token: String, query: String
    ) : Flow<PagingData<GitHubRepository>>
    {
        return getGitHubRepositoriesUseCase(
            GetGitHubRepositoriesUseCase.GetGitHubRepositoriesParams(
                token, query, getPageConfig()
            )
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 5
    )


}