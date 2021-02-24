package com.fanilo.videolist.service.api

import com.fanilo.videolist.entity.Answer
import com.fanilo.videolist.entity.VideoItem
import com.fanilo.videolist.repository.service.IRemoteVideoService
import com.fanilo.videolist.service.mapper.IRemoteVideoMapper
import com.fanilo.videolist.service.model.RemoteVideoItem
import com.fanilo.videolist.service.throwHttpException
import com.fanilo.videolist.service.throwIOException
import com.fanilo.videolist.service.webservice.IVideoWebService

import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteVideoService @Inject constructor(
    private val webService: IVideoWebService,
    private val repoMapper: IRemoteVideoMapper,
) : IRemoteVideoService {

    override suspend fun fetchVideoList(): Answer<List<VideoItem>> {
        val response: Response<List<RemoteVideoItem>>

        try {
            response = webService.fetchVideos()
        } catch (e: IOException) {
            return e.throwIOException()
        }

        response.body()?.let { result ->
            return Answer.Success(result.map {
                repoMapper.remoteToEntity(it)
            })
        } ?: run {
            return response.throwHttpException()
        }
    }
}