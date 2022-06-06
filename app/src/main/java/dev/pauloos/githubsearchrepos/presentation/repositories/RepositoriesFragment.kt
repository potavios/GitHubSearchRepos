package dev.pauloos.githubsearchrepos.presentation.repositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import dev.pauloos.githubsearchrepos.BuildConfig
import dev.pauloos.githubsearchrepos.databinding.FragmentRepositoriesBinding
import dev.pauloos.githubsearchrepos.presentation.GitHubRepositoriesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoriesFragment : Fragment()
{
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding get() = _binding!!

    private val viewModel: GitHubRepositoriesViewModel by viewModels()

    private lateinit var gitHubRepositoriesAdapter: GitHubRepositoriesAdapter

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
        observeInitialLoadState()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .gitHubRepositoriesPagingData(BuildConfig.GITHUB_TOKEN, "kotlin")
                    .collect { pagingData ->
                        gitHubRepositoriesAdapter.submitData(pagingData)
                    }
            }
        }
    }

    private fun initRepositoriesAdapter()
    {
        gitHubRepositoriesAdapter = GitHubRepositoriesAdapter()

        with(binding.recyclerViewRepositories) {
            scrollToPosition(0)
            setHasFixedSize(true)
            adapter = gitHubRepositoriesAdapter.withLoadStateFooter(
                footer = GitHubReposLoadStateAdapter {
                    gitHubRepositoriesAdapter.retry()
                }
            )
        }
    }

    private fun observeInitialLoadState()
    {
        lifecycleScope.launch {
            gitHubRepositoriesAdapter.loadStateFlow.collectLatest { loadState ->

                binding.flipperRepos.displayedChild = when (loadState.refresh)
                {
                    is LoadState.Loading ->
                    {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading ->
                    {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_REPOS
                    }
                    is LoadState.Error ->
                    {
                        setShimmerVisibility(false)
                        binding.includeViewReposErrorState.btnRetry.setOnClickListener {
                            gitHubRepositoriesAdapter.refresh()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                    else -> {FLIPPER_CHILD_ERROR}
                }

            }

        }

    }

    private fun setShimmerVisibility(visibility: Boolean)
    {
        binding.includeViewReposLoadingState.shimmerRepos.run {
            isVisible = visibility

            if (visibility)
            {
                startShimmer()
            } else stopShimmer()
        }
    }

    companion object
    {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_REPOS = 1
        private const val FLIPPER_CHILD_ERROR = 2

    }


}