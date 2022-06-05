package dev.pauloos.core.data.repository

import androidx.paging.PagingSource
import dev.pauloos.core.domain.model.GitHubRepository

interface GitHubRepositoriesRepository
{
    fun getGitHubRepositories(token: String, query: String): PagingSource<Int, GitHubRepository>
}