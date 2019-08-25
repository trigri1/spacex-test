package com.test.spacex

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.test.spacex.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SpaceXApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        LeakCanary.install(this)

        AppInjector.init(this)
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchAndroidInjector
    }

}