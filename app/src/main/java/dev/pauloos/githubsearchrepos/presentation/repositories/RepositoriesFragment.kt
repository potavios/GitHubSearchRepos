package dev.pauloos.githubsearchrepos.presentation.repositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dev.pauloos.core.domain.model.GitHubRepository
import dev.pauloos.githubsearchrepos.databinding.FragmentRepositoriesBinding

@AndroidEntryPoint
class RepositoriesFragment : Fragment()
{
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding get() = _binding!!
    private val repositoriesAdapter = RepositoriesAdapter()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentRepositoriesBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initRepositoriesAdapter()

        repositoriesAdapter.submitList(
            listOf(
                GitHubRepository("okhhtp", "square", 2000, 100, "https://avatars.githubusercontent.com/u/82592?v=4", false),
                GitHubRepository("retrofit", "circle", 3000, 200, "https://avatars.githubusercontent.com/u/82592?v=4", true),
                GitHubRepository("kotlin", "rectangle", 4000, 300, "https://avatars.githubusercontent.com/u/82592?v=4", false),
            )
        )
    }

    private fun initRepositoriesAdapter()
    {
        binding.recyclerViewRepositories.run {
            setHasFixedSize(true)
            adapter = repositoriesAdapter
        }
    }

}