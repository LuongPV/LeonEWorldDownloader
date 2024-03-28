package com.lvp.leoneworlddownloader.di

import com.lvp.leoneworlddownloader.data.apis.BrowsableApi
import com.lvp.leoneworlddownloader.data.apis.DownloadableApi
import com.lvp.leoneworlddownloader.data.apis.JavaStreamDownloadApi
import com.lvp.leoneworlddownloader.data.apis.OkHttpBrowsableApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NetModule {

    @Binds
    @Singleton
    abstract fun bindBrowsableApi(implementation: OkHttpBrowsableApi): BrowsableApi

    @Binds
    @Singleton
    abstract fun bindDownloadApi(implementation: JavaStreamDownloadApi): DownloadableApi

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }
    }

}