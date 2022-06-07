package dev.pauloos.githubsearchrepos.presentation.repositories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.githubsearchrepos.R
import dev.pauloos.githubsearchrepos.databinding.ItemGitHubRepositoryBinding

class RepositoriesViewHolder(
    itemRepositoryBinding: ItemGitHubRepositoryBinding,
) : RecyclerView.ViewHolder(itemRepositoryBinding.root)
{
    private val repositoryName = itemRepositoryBinding.tvRepositoryName
    private val userName = itemRepositoryBinding.tvRepositoryAuthor
    private val starNumber = itemRepositoryBinding.tvStarsValue
    private val forkNumber = itemRepositoryBinding.tvForksValue
    private val userPicture = itemRepositoryBinding.ivUserPic

    @SuppressLint("SetTextI18n")
    fun bind(gitHubRepository: GitHubRepository)
    {
        repositoryName.text = gitHubRepository.repositoryName
        userName.text = "by ${gitHubRepository.repositoryAuthor}"
        starNumber.text = gitHubRepository.starNumber.toString()
        forkNumber.text = gitHubRepository.forkNumber.toString()

        Glide.with(itemView)
            .load(gitHubRepository.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(userPicture)
    }

    companion object
    {
        fun create(parent:ViewGroup): RepositoriesViewHolder
        {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemGitHubRepositoryBinding.inflate(inflater, parent, false)
            return RepositoriesViewHolder(itemBinding)
        }
    }






}