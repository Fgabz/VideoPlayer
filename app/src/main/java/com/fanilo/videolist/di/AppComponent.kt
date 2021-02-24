package com.fanilo.videolist.di

import android.app.Application
import android.content.Context
import com.fanilo.videolist.CustomVideoApplication
import com.fanilo.videolist.repository.RepositoryModule
import com.fanilo.videolist.service.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        ActivityBuilder::class,
        DaggerFactoryModule::class
    ]
)
interface AppComponent {

    fun inject(application: CustomVideoApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}