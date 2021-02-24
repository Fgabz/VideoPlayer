package com.fanilo.videolist.repository

import com.fanilo.videolist.domain.IVideoRepository
import com.fanilo.videolist.repository.service.IRemoteVideoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepository @Inject constructor(private val service: IRemoteVideoService) : IVideoRepository {
    override suspend fun fetchVideoList() = service.fetchVideoList()
}