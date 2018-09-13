package com.roverdream.kothub.ui.list

import com.roverdream.kothub.ui.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(
    ListModule::class
))
interface ListComponent {

    fun injectTo(activity: ListActivity)
}