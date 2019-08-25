package com.test.spacex.ui.main

import androidx.lifecycle.ViewModelProvider
import com.test.spacex.data.DataManager
import com.test.spacex.rx.SchedulerProvider
import com.test.spacex.ui.base.BaseViewModelFactory
import com.test.spacex.ui.main.adapter.LaunchesAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): MainViewModel {
        return MainViewModel(dataManager, schedulerProvider, compositeDisposable)
    }

    @Provides
    fun provideMainViewModelFactory(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return BaseViewModelFactory(mainViewModel)
    }

    @Provides
    fun provideLaunchesAdapter(): LaunchesAdapter {
        return LaunchesAdapter()
    }


}