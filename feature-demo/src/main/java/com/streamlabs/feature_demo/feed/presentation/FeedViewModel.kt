package com.streamlabs.feature_demo.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.streamlabs.feature_demo.feed.business.GetVideos
import com.streamlabs.feature_demo.feed.ui.model.VideoUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(private val getVideos: GetVideos) : ViewModel() {
    private val _myUiState = MutableStateFlow<List<VideoUi>>(emptyList())
    val myUiState: StateFlow<List<VideoUi>> = _myUiState

    fun loadVideos() {
        viewModelScope.launch {
            val result = getVideos()
            _myUiState.value = result
        }
    }
}

class FeedViewModelFactory(
    private val getVideos: GetVideos
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedViewModel(getVideos) as T
    }
}
