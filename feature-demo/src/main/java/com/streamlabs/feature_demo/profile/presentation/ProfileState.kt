package com.streamlabs.feature_demo.profile.presentation

data class ProfileState(
    val userName: String? = null,
    val userTitle: String? = null,
    val userLocalImage: String? = null,
    val userVideos: Int? = null,
    val userFollowing: Int? = null,
    val userFans: Int? = null,
    val userHearts: Int? = null
)
