package dev.pauloos.githubsearchrepos.presentation

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import dev.pauloos.core.usercase.GetGitHubRepositoriesUseCase
import dev.pauloos.testing.MainCoroutineRule
import dev.pauloos.testing.model.GitHubRepositoryFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
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
class GitHubRepositoriesViewModelTest
{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getGitHubRepositoriesUseCase: GetGitHubRepositoriesUseCase

    private lateinit var gitHubRepositoriesViewModel: GitHubRepositoriesViewModel

    private val gitHubRepositoryFactory = GitHubRepositoryFactory()

    private val pagingDataGitHubRepository = PagingData.from(
        listOf(
            gitHubRepositoryFactory.create(GitHubRepositoryFactory.GitHubRepo.OkHttp),
            gitHubRepositoryFactory.create(GitHubRepositoryFactory.GitHubRepo.RxKotlin)
        )
    )

    @Before
    fun setUp()
    {
        gitHubRepositoriesViewModel = GitHubRepositoriesViewModel(getGitHubRepositoriesUseCase)
    }

    @Test
    fun `should validate the paging data object values when calling gitHubRepositoriesPagingData`() =
        runBlockingTest {
            whenever(
                getGitHubRepositoriesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataGitHubRepository
                )
            )

            val result = gitHubRepositoriesViewModel.gitHubRepositoriesPagingData("", "")
            assertEquals(1, result.count())
        }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception` () =
        runBlockingTest {
            whenever(getGitHubRepositoriesUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            gitHubRepositoriesViewModel.gitHubRepositoriesPagingData("","")

        }
}