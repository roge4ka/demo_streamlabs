package com.streamlabs.feature_demo.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.streamlabs.domain_demo.DemoRepository
import com.streamlabs.feature_demo.R
import com.streamlabs.feature_demo.databinding.FragmentProfileBinding
import com.streamlabs.feature_demo.profile.business.GetUserProfileState
import com.streamlabs.feature_demo.profile.presentation.ProfileState
import com.streamlabs.feature_demo.profile.presentation.ProfileViewModel
import com.streamlabs.feature_demo.profile.presentation.ProfileViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels {
        //i don't use dagger or koin here, just example
        ProfileViewModelFactory(GetUserProfileState(DemoRepository()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectChanges()
        viewModel.loadProfileData()
        initClickListeners()
    }

    private fun collectChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.myUiState.collect { showProfile(it) }
        }
    }

    private fun initClickListeners() {
        binding.editButton.setOnClickListener {
            Snackbar.make(binding.root, "Not implemented", Snackbar.LENGTH_SHORT).show()
        }
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun showProfile(profileState: ProfileState) {
        if (profileState.userLocalImage != null) {
            binding.logoImage.visibility = View.VISIBLE

            val uri = profileState.userLocalImage

            val imageResource =
                resources.getIdentifier(uri, null, requireContext().packageName)
            binding.logoImage.load(imageResource) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        } else {
            binding.logoImage.visibility = View.GONE
        }

        profileState.userVideos?.let {
            binding.userVideos.text = requireContext().getString(R.string.profile_videos, it)
        }

        profileState.userFollowing?.let {
            binding.followingCountText.text = it.toString()
        }

        profileState.userFans?.let {
            binding.fansCountText.text = it.toString()
        }

        profileState.userHearts?.let {
            binding.heartsCountText.text = it.toString()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}