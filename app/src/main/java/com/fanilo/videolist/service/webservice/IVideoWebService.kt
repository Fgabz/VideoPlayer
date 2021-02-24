package com.fanilo.videolist.service.webservice

import com.fanilo.videolist.service.model.RemoteVideoItem
import retrofit2.Response
import retrofit2.http.GET

interface IVideoWebService {

    //For the test I wanted to target this endpoint to get more up to date repo
    //I could have target /repositories
    @GET("/v0/b/powder-c9282.appspot.com/o/test%2Fstatic_feed.json?alt=media&token=c5bbde3a-129b-449e-a79e-d2a0ccffbd0f")
    suspend fun fetchVideos(
    ): Response<List<RemoteVideoItem>>
}