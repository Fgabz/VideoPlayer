package com.fanilo.videolist.repository

import com.fanilo.videolist.domain.IVideoRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideVideoRepository(userRepository: VideoRepository): IVideoRepository
}