package com.fanilo.videolist.di


import com.fanilo.videolist.core.PerActivity
import com.fanilo.videolist.feature.HomeActivity
import com.fanilo.videolist.feature.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            HomeModule::class
        ]
    )
    abstract fun contributeHomeActivityInjector(): HomeActivity
}