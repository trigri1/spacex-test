package com.test.spacex.di.modules

import com.test.spacex.ui.detail.DetailActivity
import com.test.spacex.ui.detail.DetailActivityModule
import com.test.spacex.ui.main.MainActivity
import com.test.spacex.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, CommonInjectionModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class, CommonInjectionModule::class])
     abstract fun bindDetailActivity(): DetailActivity

}