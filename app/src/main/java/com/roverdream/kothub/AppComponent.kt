package com.roverdream.kothub7

import com.roverdream.kothub.App
import com.roverdream.kothub.AppModule
import com.roverdream.kothub.data.network.NetworkModule
import com.roverdream.kothub.data.remote.ApiModule
import com.roverdream.kothub.ui.list.ListComponent
import com.roverdream.kothub.ui.list.ListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ApiModule::class
))

interface AppComponent {

    // Injectors
    fun injectTo(app: App)

    fun plus(module: ListModule): ListComponent
}