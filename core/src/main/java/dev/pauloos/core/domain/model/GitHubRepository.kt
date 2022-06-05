package dev.pauloos.core.domain.model

data class GitHubRepository(
    val repositoryName: String,
    val repositoryAuthor: String,
    val starNumber: Int,
    val forkNumber: Int,
    val imageUrl: String,
    val favorite: Boolean
)
