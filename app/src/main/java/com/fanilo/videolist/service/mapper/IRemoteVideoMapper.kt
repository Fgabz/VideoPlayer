package com.fanilo.videolist.service.mapper

import com.fanilo.videolist.entity.VideoItem
import com.fanilo.videolist.service.model.RemoteVideoItem

interface IRemoteVideoMapper {
    suspend fun remoteToEntity(remote: RemoteVideoItem): VideoItem
}