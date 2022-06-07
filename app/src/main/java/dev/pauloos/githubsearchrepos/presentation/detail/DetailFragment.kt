package dev.pauloos.githubsearchrepos.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.pauloos.githubsearchrepos.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : Fragment()
{
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root




    override fun onDestroy()
    {
        super.onDestroy()
        _binding = null
    }

}