package com.roverdream.kothub.ui.detail

import android.support.v7.app.AppCompatActivity
import com.roverdream.kothub.data.remote.model.Repo
import com.roverdream.kothub.ui.base.ActivityModule
import dagger.Module
import dagger.Provides

@Module
class DetailModule(activity: AppCompatActivity, val repo: Repo) : ActivityModule(activity) {

    @Provides
    fun provideRepo(): Repo = repo
}