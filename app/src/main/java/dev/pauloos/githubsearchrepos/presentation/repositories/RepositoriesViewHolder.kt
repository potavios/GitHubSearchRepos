package dev.pauloos.githubsearchrepos.presentation.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.pauloos.core.domain.model.Repository
import dev.pauloos.githubsearchrepos.R
import dev.pauloos.githubsearchrepos.databinding.ItemRepositoryBinding

class RepositoriesViewHolder(
    itemRepositoryBinding: ItemRepositoryBinding,
) : RecyclerView.ViewHolder(itemRepositoryBinding.root)
{
    private val repositoryName = itemRepositoryBinding.tvRepositoryName
    private val userName = itemRepositoryBinding.tvRepositoryAuthor
    private val starNumber = itemRepositoryBinding.tvStarsValue
    private val forkNumber = itemRepositoryBinding.tvForksValue
    private val userPicture = itemRepositoryBinding.ivUserPic
    private val favoriteIcon = itemRepositoryBinding.ivFavoriteIcon

    fun bind(repository: Repository)
    {
        repositoryName.text = repository.repositoryName
        userName.text = repository.repositoryAuthor
        starNumber.text = repository.starNumber.toString()
        forkNumber.text = repository.forkNumber.toString()

        Glide.with(itemView)
            .load(repository.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(userPicture)

        if (repository.favorite)
        {
            Glide.with(itemView)
                .load(R.drawable.ic_favorite_menu)
                .fallback(R.drawable.ic_favorite_off)
                .into(favoriteIcon)
        }

    }

    companion object
    {
        fun create(parent:ViewGroup): RepositoriesViewHolder
        {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemRepositoryBinding.inflate(inflater, parent, false)
            return RepositoriesViewHolder(itemBinding)
        }
    }






}