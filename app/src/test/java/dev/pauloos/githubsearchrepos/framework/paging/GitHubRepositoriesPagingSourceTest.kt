package dev.pauloos.githubsearchrepos.framework.paging

import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import dev.pauloos.core.data.repository.GitHubRepositoriesRemoteDataSource
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.githubsearchrepos.factory.response.DataWrapperResponseFactory
import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import dev.pauloos.testing.MainCoroutineRule
import dev.pauloos.testing.model.GitHubRepositoryFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GitHubRepositoriesPagingSourceTest
{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: GitHubRepositoriesRemoteDataSource<DataWrapperResponse>

    private val dataWrapperResponseFactory = DataWrapperResponseFactory()

    private val gitHubRepoFactory = GitHubRepositoryFactory()

    private lateinit var gitHubRepositoriesPagingSource: GitHubRepositoriesPagingSource

    @Before
    fun setUp()
    {
        gitHubRepositoriesPagingSource =
            GitHubRepositoriesPagingSource(remoteDataSource, "","")
    }

    @Test
    fun `should return a success load result when load is called`() = runBlockingTest {

        whenever(remoteDataSource.fetchGitHubRepositories(any(), any()))
            .thenReturn(dataWrapperResponseFactory.create())

            val result = gitHubRepositoriesPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    null,
                    loadSize = 2,
                    false
                )
            )

        val expected = listOf(
            gitHubRepoFactory.create(GitHubRepositoryFactory.GitHubRepo.OkHttp),
            gitHubRepoFactory.create(GitHubRepositoryFactory.GitHubRepo.RxKotlin),
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = 2
            ),
            result
        )
    }

    @Test
    fun `should return an error load result when load is called`() = runBlockingTest {

        val exception = RuntimeException()
        whenever(remoteDataSource.fetchGitHubRepositories(any(), any()))
            .thenThrow(exception)

        val result = gitHubRepositoriesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, GitHubRepository>(exception),
            result
        )
    }

}