package com.streamlabs.feature_demo.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.streamlabs.feature_demo.profile.business.GetUserProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userProfileState: GetUserProfileState) : ViewModel() {

    private val _myUiState = MutableStateFlow<ProfileState>(ProfileState())
    val myUiState: StateFlow<ProfileState> = _myUiState

    fun loadProfileData() {
        viewModelScope.launch {
            val result = userProfileState()
            _myUiState.value = result
        }
    }
}

class ProfileViewModelFactory(
    private val userProfileState: GetUserProfileState
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userProfileState) as T
    }
}
