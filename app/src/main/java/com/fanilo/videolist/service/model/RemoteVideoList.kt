package com.fanilo.videolist.service.model

import com.google.gson.annotations.SerializedName

data class RemoteVideoItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("game")
    val game: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("video_url")
    val videoUrl: String
)
