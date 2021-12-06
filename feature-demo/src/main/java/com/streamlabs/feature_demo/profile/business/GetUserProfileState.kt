package com.streamlabs.feature_demo.profile.business

import com.streamlabs.domain_demo.DemoRepository
import com.streamlabs.feature_demo.profile.presentation.ProfileState

class GetUserProfileState(private val demoRepository: DemoRepository) {

    suspend operator fun invoke(): ProfileState {
        val userInfo = demoRepository.getUserInfo()
        return userInfo.run {
            ProfileState(
                userName,
                userTitle,
                userLocalImage,
                userVideos,
                userFollowing,
                userFans,
                userHearts
            )
        }
    }
}