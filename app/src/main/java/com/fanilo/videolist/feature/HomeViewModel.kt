package com.fanilo.videolist.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanilo.videolist.core.PerActivity
import com.fanilo.videolist.domain.usecase.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@PerActivity
class HomeViewModel @Inject constructor(
    private val loginUseCase: HomeUseCase,
    private val presenter: IHomePresenter<IHomeView>
) : ViewModel(), IHomeView {

    private var onViewReadyCalled = false

    private val _mutableVideoList = MutableLiveData<List<VideoViewState>>()

    val mutableVideoList: LiveData<List<VideoViewState>> = _mutableVideoList

    fun onCreate() {
        presenter.onAttachView(this)
    }

    fun onViewReady() = viewModelScope.launch(Dispatchers.IO) {
        if (!onViewReadyCalled) {
            loginUseCase.fetchHomeVideoList()
            onViewReadyCalled = true
        }
    }

    override fun displayVideos(result: List<VideoViewState>) {
        _mutableVideoList.postValue(result)
    }

    override fun onCleared() {
        presenter.onDetachView()
        super.onCleared()
    }
}