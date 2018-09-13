package com.roverdream.kothub

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides @Singleton
    fun provideApplication(): Application = app

    @Provides @Singleton @AppQualifier
    fun provideContext(): Context = app.baseContext

    @Provides @Singleton
    fun provideResources(): Resources = app.resources

    @Provides @Singleton
    fun provideLayoutInflater(@AppQualifier context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    fun provideDebugTree(): Timber.DebugTree = Timber.DebugTree()
}