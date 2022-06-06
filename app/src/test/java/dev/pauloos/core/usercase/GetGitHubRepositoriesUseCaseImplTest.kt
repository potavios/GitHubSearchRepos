package dev.pauloos.core.usercase

import androidx.paging.PagingConfig
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.pauloos.core.data.repository.GitHubRepositoriesRepository
import dev.pauloos.testing.MainCoroutineRule
import dev.pauloos.testing.model.GitHubRepositoryFactory
import dev.pauloos.testing.pagingsource.PagingSourceFactory
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetGitHubRepositoriesUseCaseImplTest
{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: GitHubRepositoriesRepository

    private lateinit var getGitHubUseCase: GetGitHubRepositoriesUseCase

    private val repoFake = GitHubRepositoryFactory().create(GitHubRepositoryFactory.GitHubRepo.OkHttp)

    private val fakePagingSource = PagingSourceFactory().create(listOf(repoFake))

    @Before
    fun setUp()
    {
        getGitHubUseCase = GetGitHubRepositoriesUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow page data creation when invoke from use case is called`() =
        runBlockingTest {
            whenever(repository.getGitHubRepositories("", ""))
                .thenReturn(fakePagingSource)

            val result = getGitHubUseCase
                .invoke(GetGitHubRepositoriesUseCase.GetGitHubRepositoriesParams(
                    "","", PagingConfig(30)))

            verify(repository).getGitHubRepositories("","")

            assertNotNull(result.first())
        }

}