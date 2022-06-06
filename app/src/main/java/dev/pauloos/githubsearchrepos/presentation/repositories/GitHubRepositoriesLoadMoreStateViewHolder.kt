package dev.pauloos.githubsearchrepos.presentation.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import dev.pauloos.githubsearchrepos.databinding.ItemReposLoadMoreStateBinding

class GitHubRepositoriesLoadMoreStateViewHolder(
    itemBinding: ItemReposLoadMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root)
{
    private val binding = ItemReposLoadMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadingMore
    private val textTryAgainMessage = binding.tvTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState)
    {
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object
    {
        fun create(parent: ViewGroup, retry: () -> Unit) : GitHubRepositoriesLoadMoreStateViewHolder
        {
            val itemBinding = ItemReposLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return GitHubRepositoriesLoadMoreStateViewHolder(itemBinding, retry)
        }
    }



}