package com.test.spacex.ui.detail

import androidx.lifecycle.ViewModelProvider
import com.test.spacex.data.DataManager
import com.test.spacex.rx.SchedulerProvider
import com.test.spacex.ui.base.BaseViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class DetailActivityModule {

    @Provides
    fun provideMainViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): DetailViewModel {
        return DetailViewModel(dataManager, schedulerProvider, compositeDisposable)
    }

    @Provides
    fun provideMainViewModelFactory(detailViewModel: DetailViewModel): ViewModelProvider.Factory {
        return BaseViewModelFactory(detailViewModel)
    }


}