package com.fanilo.videolist.di

import dagger.Binds
import dagger.Module

@Module
abstract class DaggerFactoryModule {

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: DaggerViewModelFactory): IDaggerFactoryViewModel
}