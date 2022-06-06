package dev.pauloos.githubsearchrepos.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pauloos.core.data.repository.GitHubRepositoriesRemoteDataSource
import dev.pauloos.core.data.repository.GitHubRepositoriesRepository
import dev.pauloos.githubsearchrepos.framework.network.GitHubRepositoriesRepositoryImpl
import dev.pauloos.githubsearchrepos.framework.network.response.DataWrapperResponse
import dev.pauloos.githubsearchrepos.framework.remote.RetrofitGitHubRepositoriesDataSource

@Module
@InstallIn(SingletonComponent::class)
interface GitHubRepositoryModule
{
    @Binds
    fun bindGitHubRepositoriesRepository(
        repository: GitHubRepositoriesRepositoryImpl
    ): GitHubRepositoriesRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitGitHubRepositoriesDataSource
    ): GitHubRepositoriesRemoteDataSource<DataWrapperResponse>


}