package com.roverdream.kothub.ui.detail

import com.roverdream.kothub.ui.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(
        DetailModule::class
))
interface DetailComponent {
    fun injectTo(activity: DetailActivity)
}
