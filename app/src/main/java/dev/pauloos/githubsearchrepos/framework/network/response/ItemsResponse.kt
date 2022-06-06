package dev.pauloos.githubsearchrepos.framework.network.response

import com.google.gson.annotations.SerializedName
import dev.pauloos.core.domain.model.GitHubRepository

data class ItemsResponse(
    val id: String,
    val name: String,
    val owner: OwnerResponse,
    @SerializedName("stargazers_count")
    val totalStars: Int,
    @SerializedName("forks_count")
    val totalForks: Int
)

fun ItemsResponse.toGitHubRepositoryModel(): GitHubRepository
{
    return GitHubRepository(
        repositoryName = this.name,
        repositoryAuthor = this.owner.login,
        starNumber = this.totalStars,
        forkNumber = this.totalForks,
        imageUrl = this.owner.avatarUrl
    )
}
