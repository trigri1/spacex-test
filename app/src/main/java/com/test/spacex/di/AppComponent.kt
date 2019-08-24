package com.test.spacex.di

import com.test.spacex.SpaceXApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<SpaceXApplication> {

    override fun inject(instance: SpaceXApplication?)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: SpaceXApplication): Builder

        fun build(): AppComponent
    }

}