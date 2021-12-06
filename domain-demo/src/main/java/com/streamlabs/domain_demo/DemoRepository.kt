package com.streamlabs.domain_demo

import com.streamlabs.domain_demo.model.UserModel
import com.streamlabs.domain_demo.model.VideosModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DemoRepository {

    fun getUserInfo(): UserModel {
        val fileContent = this::class.java.classLoader.getResource("user.json")?.readText()
        return fileContent?.let { fileContent ->
            Json.decodeFromString<UserModel>(fileContent)
        } ?: error("File don't exist")
    }

    fun getUserVideos(): List<VideosModel> {
        val fileContent = this::class.java.classLoader.getResource("videos.json")?.readText()
        return fileContent?.let { fileContent ->
            Json.decodeFromString<List<VideosModel>>(fileContent)
        } ?: error("File don't exist")
    }
}