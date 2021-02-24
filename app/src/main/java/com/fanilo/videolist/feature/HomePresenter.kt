package com.fanilo.videolist.feature

import com.fanilo.videolist.core.PerActivity
import com.fanilo.videolist.entity.VideoItem
import javax.inject.Inject

@PerActivity
class HomePresenter @Inject constructor() : IHomePresenter<IHomeView> {
    override var view: IHomeView? = null

    override fun displayVideos(videoList: List<VideoItem>) {
        val result = videoList.map { mapEntityToViewState(it) }

        view?.displayVideos(result)
    }

    private fun mapEntityToViewState(item: VideoItem): VideoViewState {
        return VideoViewState(
            url = item.videoUrl,
            title = item.title
        )
    }
}