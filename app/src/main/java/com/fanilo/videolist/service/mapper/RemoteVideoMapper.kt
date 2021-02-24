package com.fanilo.videolist.service.mapper

import com.fanilo.videolist.entity.VideoItem
import com.fanilo.videolist.service.model.RemoteVideoItem
import javax.inject.Inject

class RemoteVideoMapper @Inject constructor() : IRemoteVideoMapper {

    override suspend fun remoteToEntity(remote: RemoteVideoItem) = VideoItem(
        id = remote.id,
        game = remote.game,
        title = remote.title,
        videoUrl = remote.videoUrl,
        author = remote.author
    )
}