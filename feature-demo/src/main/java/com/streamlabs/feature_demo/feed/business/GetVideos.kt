package com.streamlabs.feature_demo.feed.business

import com.streamlabs.domain_demo.DemoRepository
import com.streamlabs.feature_demo.feed.ui.model.VideoUi

class GetVideos(private val demoRepository: DemoRepository) {

    operator fun invoke(): List<VideoUi> {
        return demoRepository.getUserVideos().map {
            it.run {
                VideoUi(
                    videoDescription,
                    videoPath,
                    videoNumberLikes,
                    videoNumberComments,
                    userId,
                    userName,
                    userImagePath
                )
            }
        }
    }
}