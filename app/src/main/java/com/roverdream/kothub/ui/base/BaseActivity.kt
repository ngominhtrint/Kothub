package com.roverdream.kothub.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.roverdream.kothub.App
import com.roverdream.kothub7.AppComponent

abstract class BaseActivity: AppCompatActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)
    }

    abstract fun injectDependencies(graph: AppComponent)
}