package com.oddhov.signupformvalidation.ui

import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [(MainActivityComponent.MainActivityModule::class)])
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    @Module
    class MainActivityModule internal constructor(
            private val activity: com.oddhov.signupformvalidation.ui.MainActivity) {

        @Provides
        fun activity(): com.oddhov.signupformvalidation.ui.MainActivity {
            return activity
        }
    }
}
