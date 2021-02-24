package com.fanilo.videolist.domain

import com.fanilo.videolist.entity.Answer
import com.fanilo.videolist.entity.VideoItem

interface IVideoRepository {
    suspend fun fetchVideoList(): Answer<List<VideoItem>>
}