package com.fanilo.videolist.repository.service

import com.fanilo.videolist.entity.Answer
import com.fanilo.videolist.entity.VideoItem

interface IRemoteVideoService {
    suspend fun fetchVideoList(): Answer<List<VideoItem>>
}