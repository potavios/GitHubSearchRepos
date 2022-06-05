package dev.pauloos.core.data.repository

interface GitHubRepositoriesRemoteDataSource<T>
{
    suspend fun fetchGitHubRepositories(token: String, queries: Map<String, String>): T
}