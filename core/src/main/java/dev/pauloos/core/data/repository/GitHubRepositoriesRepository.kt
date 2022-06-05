package dev.pauloos.core.data.repository

import androidx.paging.PagingSource
import dev.pauloos.core.domain.model.Repository

interface GitHubRepositoriesRepository
{
    fun getGitHubRepositories(query: String) : PagingSource<Int, Repository>
}