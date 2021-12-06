package com.streamlabs.domain_demo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosModel(
    @SerialName("video_description")
    val videoDescription: String,
    @SerialName("video_path")
    val videoPath: String,
    @SerialName("video_number_likes")
    val videoNumberLikes: Int,
    @SerialName("video_number_comments")
    val videoNumberComments: Int,
    @SerialName("user_id")
    val userId: String,
    @SerialName("user_name")
    val userName: String,
    @SerialName("user_image_path")
    val userImagePath: String
)
