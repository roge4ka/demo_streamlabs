package com.streamlabs.domain_demo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel (
    @SerialName("user_name")
    val userName: String,
    @SerialName("user_title")
    val userTitle: String,
    @SerialName("user_local_image")
    val userLocalImage: String,
    @SerialName("user_videos")
    val userVideos: Int,
    @SerialName("user_following")
    val userFollowing: Int,
    @SerialName("user_fans")
    val userFans: Int,
    @SerialName("user_hearts")
    val userHearts: Int
)
