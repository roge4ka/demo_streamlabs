package com.streamlabs.feature_demo.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.streamlabs.domain_demo.DemoRepository
import com.streamlabs.feature_demo.R
import com.streamlabs.feature_demo.databinding.FragmentFeedBinding
import com.streamlabs.feature_demo.feed.business.GetVideos
import com.streamlabs.feature_demo.feed.presentation.FeedViewModel
import com.streamlabs.feature_demo.feed.presentation.FeedViewModelFactory
import com.streamlabs.feature_demo.feed.ui.recycler.FeedAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = FeedAdapter()

    private val viewModel: FeedViewModel by viewModels {
        //i don't use dagger or koin here, just example
        FeedViewModelFactory(GetVideos(DemoRepository()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        collectChanges()
        initAdapter()
        viewModel.loadVideos()
    }

    private fun initClickListeners() {
        binding.buttonPrevious.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun collectChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.myUiState.collect { adapter.setItems(it) }
        }
    }

    private fun initAdapter() {
        binding.recycler.adapter = adapter
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        binding.recycler.addItemDecoration(dividerItemDecoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}