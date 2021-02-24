package com.fanilo.videolist.service

import com.fanilo.videolist.repository.service.IRemoteVideoService
import com.fanilo.videolist.service.api.RemoteVideoService
import com.fanilo.videolist.service.mapper.IRemoteVideoMapper
import com.fanilo.videolist.service.mapper.RemoteVideoMapper
import com.fanilo.videolist.service.webservice.IVideoWebService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RemoteModule.AbstractionModule::class])
class RemoteModule {

    @Module
    abstract class AbstractionModule {
        @Binds
        abstract fun provideRemoteVideoService(remoteVideoService: RemoteVideoService): IRemoteVideoService

        @Binds
        abstract fun provideRemoteVideoMapper(mapper: RemoteVideoMapper): IRemoteVideoMapper
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(12, TimeUnit.SECONDS)

        httpClient
            .addInterceptor(loggingInterceptor)

        return httpClient.build()
    }

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesVideoWebService(retrofit: Retrofit): IVideoWebService {
        return retrofit.create(IVideoWebService::class.java)
    }
}