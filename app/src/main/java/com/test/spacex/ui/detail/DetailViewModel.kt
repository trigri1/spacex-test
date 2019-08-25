package com.test.spacex.ui.detail

import com.test.spacex.data.DataManager
import com.test.spacex.rx.SchedulerProvider
import com.test.spacex.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class DetailViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel<DetailContract>(dataManager, schedulerProvider, compositeDisposable) {


}