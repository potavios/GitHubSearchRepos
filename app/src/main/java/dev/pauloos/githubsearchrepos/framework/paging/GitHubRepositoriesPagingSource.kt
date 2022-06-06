package dev.pauloos.githubsearchrepos.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.pauloos.core.data.repository.GitHubRepositoriesRemoteDataSource
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import dev.pauloos.githubsearchrepos.framework.network.response.toGitHubRepositoryModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

class GitHubRepositoriesPagingSource(
    private val remoteDataSource: GitHubRepositoriesRemoteDataSource<DataWrapperResponse>,
    private val token: String,
    private val query: String
) : PagingSource<Int, GitHubRepository>()
{
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepository>
    {
        return try
        {
            /** Query parameters: q -> "kotlin", sort -> "stars", order -> "desc", page -> "1", per_page -> "5"*/
            val page = params.key ?: 1
            val queries : HashMap<String, String> = hashMapOf()

            if (query.isNotEmpty())
            {
                queries["q"] = query
                queries["sort"] = "stars"
                queries["order"] = "desc"
                queries["page"] = page.toString()
            }

            val response = remoteDataSource
                .fetchGitHubRepositories(token, queries)

            remoteDataSource
                .fetchGitHubRepositories(token, queries)
            val responseTotalItems = response.totalCount


            LoadResult.Page(
                data =  response.items.map { it.toGitHubRepositoryModel() },
                prevKey = null,
                nextKey = if (page < responseTotalItems)
                {
                    page + LIMIT
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
