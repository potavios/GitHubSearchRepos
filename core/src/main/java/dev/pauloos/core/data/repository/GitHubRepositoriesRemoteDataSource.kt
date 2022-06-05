package dev.pauloos.core.data.repository

interface GitHubRepositoriesRemoteDataSource<T>
{
    suspend fun fetchGitHubRepositories(queries: Map<String, String>): T
}