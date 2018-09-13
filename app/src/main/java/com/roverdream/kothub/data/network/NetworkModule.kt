package com.roverdream.kothub.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.roverdream.kothub.AppQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun provideConnectivityManager(@AppQualifier context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides @Singleton
    fun provideNetworkInteractor(networkInteractorImpl: NetworkInteractorImpl): NetworkInteractor = networkInteractorImpl
}