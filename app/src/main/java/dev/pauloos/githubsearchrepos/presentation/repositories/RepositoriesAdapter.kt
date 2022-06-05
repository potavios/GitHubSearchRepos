package dev.pauloos.githubsearchrepos.presentation.repositories

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.pauloos.core.domain.model.GitHubRepository

class RepositoriesAdapter : ListAdapter<GitHubRepository, RepositoriesViewHolder>(diffCallback)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder
    {
        return RepositoriesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int)
    {
        holder.bind(getItem(position))
    }

    companion object
    {
        private val diffCallback = object : DiffUtil.ItemCallback<GitHubRepository>()
        {
            override fun areItemsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean
            {
                return oldItem.repositoryName == newItem.repositoryName
            }

            override fun areContentsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean
            {
                return oldItem == newItem

            }
        }
    }
}