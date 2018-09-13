package com.roverdream.kothub

import android.app.Application
import com.roverdream.kothub7.AppComponent
import com.roverdream.kothub7.DaggerAppComponent
import dagger.Lazy
import timber.log.Timber
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var debugTree: Lazy<Timber.DebugTree>

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependencyGraph()

        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree.get())
        }
    }

    private fun initDependencyGraph() {
        graph = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        graph.injectTo(this)
    }
}