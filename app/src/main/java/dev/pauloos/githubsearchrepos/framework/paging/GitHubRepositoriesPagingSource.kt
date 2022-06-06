package dev.pauloos.githubsearchrepos.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.pauloos.core.data.repository.GitHubRepositoriesRemoteDataSource
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.githubsearchrepos.BuildConfig
import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import dev.pauloos.githubsearchrepos.framework.network.response.toGitHubRepositoryModel

class GitHubRepositoriesPagingSource(
    private val remoteDataSource: GitHubRepositoriesRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, GitHubRepository>()
{
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepository>
    {
        return try {

            /**
            Query parameters:
             q -> "kotlin"
             sort -> "stars"
             order -> "desc"
             page -> "1"
             per_page -> "5"
            */


            val page = params.key ?: 0
            val queries : HashMap<String, String> = hashMapOf()

            if (query.isNotEmpty())
            {
                queries["q="] = query
            }

            queries["page"] = page.toString()

            val response = remoteDataSource
                .fetchGitHubRepositories(BuildConfig.GITHUB_TOKEN, queries)

            val pageNumber = 1
            val responseTotalItems = response.totalCount

            LoadResult.Page(
                data = response.items.map { it.toGitHubRepositoryModel() },
                prevKey = null,
                nextKey = if (pageNumber < responseTotalItems)
                {
                    pageNumber + LIMIT
                } else null
            )

        }
        catch (exception: Exception)
        {
            LoadResult.Error(exception)

        }

    }

    override fun getRefreshKey(state: PagingState<Int, GitHubRepository>): Int?
    {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object
    {
        private const val LIMIT = 1
    }
}
