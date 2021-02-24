package com.fanilo.videolist.feature

import com.fanilo.videolist.core.PerActivity
import com.fanilo.videolist.domain.IVideoRepository
import com.fanilo.videolist.domain.usecase.HomeUseCase
import com.fanilo.videolist.entity.onSuccess
import javax.inject.Inject

@PerActivity
class HomeInteractor @Inject constructor(
    private val presenter: IHomePresenter<IHomeView>,
    private val repository: IVideoRepository
) : HomeUseCase {

    override suspend fun fetchHomeVideoList() {
        repository.fetchVideoList().onSuccess {
            presenter.displayVideos(it)
        }
    }
}