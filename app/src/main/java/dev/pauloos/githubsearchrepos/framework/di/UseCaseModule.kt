package dev.pauloos.githubsearchrepos.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.pauloos.core.usercase.GetGitHubRepositoriesUseCase
import dev.pauloos.core.usercase.GetGitHubRepositoriesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule
{
    @Binds
    fun bindGitHubRepositoriesUseCase(useCase: GetGitHubRepositoriesUseCaseImpl
    ): GetGitHubRepositoriesUseCase
}