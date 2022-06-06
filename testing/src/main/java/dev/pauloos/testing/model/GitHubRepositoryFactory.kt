package dev.pauloos.testing.model

import dev.pauloos.core.domain.model.GitHubRepository

class GitHubRepositoryFactory
{
    fun create(gitHubRepo: GitHubRepo) = when (gitHubRepo)
    {
        GitHubRepo.OkHttp -> GitHubRepository(
            "okHttp",
            "square",
            1900,
            345,
            "https://avatars.githubusercontent.com/u/82592?v=4"
        )

        GitHubRepo.RxKotlin -> GitHubRepository(
            "RxKotlin",
            "ReactiveX",
            6861,
            461,
            "https://avatars.githubusercontent.com/u/82592?v=4"
        )

    }

    sealed class GitHubRepo
    {
        object OkHttp : GitHubRepo()
        object RxKotlin : GitHubRepo()
    }

}