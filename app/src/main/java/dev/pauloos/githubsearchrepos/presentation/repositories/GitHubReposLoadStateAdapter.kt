package dev.pauloos.githubsearchrepos.presentation.repositories

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class GitHubReposLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GitHubRepositoriesLoadMoreStateViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState
    ) = GitHubRepositoriesLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: GitHubRepositoriesLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

}