package com.fanilo.videolist.feature

import androidx.lifecycle.ViewModel
import com.fanilo.videolist.core.ViewModelKey
import com.fanilo.videolist.domain.usecase.HomeUseCase
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @Binds
    abstract fun providePresenter(presenter: HomePresenter): IHomePresenter<IHomeView>

    @Binds
    abstract fun provideInteractor(interactor: HomeInteractor): HomeUseCase

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindToDoViewModel(viewModel: HomeViewModel): ViewModel
}