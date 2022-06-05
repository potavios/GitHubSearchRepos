package dev.pauloos.githubsearchrepos.presentation.repositories

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.pauloos.core.domain.model.Repository

class RepositoriesAdapter : ListAdapter<Repository, RepositoriesViewHolder>(diffCallback)
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
        private val diffCallback = object : DiffUtil.ItemCallback<Repository>()
        {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean
            {
                return oldItem.repositoryName == newItem.repositoryName
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean
            {
                return oldItem == newItem

            }
        }
    }
}