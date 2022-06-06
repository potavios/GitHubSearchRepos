package dev.pauloos.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.pauloos.core.domain.model.GitHubRepository

class PagingSourceFactory
{
    fun create(gitHubRepos: List<GitHubRepository>) = object : PagingSource<Int, GitHubRepository>()
    {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepository>
        {
            return LoadResult.Page(
                data = gitHubRepos,
                prevKey = null,
                nextKey = 2
            )
        }
        override fun getRefreshKey(state: PagingState<Int, GitHubRepository>) = 1
    }
}