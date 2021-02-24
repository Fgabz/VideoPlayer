package com.fanilo.videolist.feature

import com.fanilo.videolist.entity.VideoItem

interface IHomePresenter<V : IHomeView> {
    var view: V?

    fun onAttachView(view: V) {
        this.view = view
    }

    fun onDetachView() {
        this.view = null
    }

    fun displayVideos(videoList: List<VideoItem>)
}