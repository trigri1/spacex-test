package com.test.spacex.ui.main

import androidx.lifecycle.MutableLiveData
import com.test.spacex.data.DataManager
import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.rx.SchedulerProvider
import com.test.spacex.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BaseViewModel<MainContract>(dataManager, schedulerProvider, compositeDisposable) {

    val launchesList = MutableLiveData<List<LaunchModel>>()

    var originalList: List<LaunchModel>? = null

    fun getLaunches() {
        viewContract?.showProgressBar()
        compositeDisposable.add(
            dataManager.getLaunches()
                ?.subscribeOn(schedulerProvider.io())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe({
                    viewContract?.hideProgressBar()
                    originalList = it
                    launchesList.value = originalList
                }, {
                    viewContract?.hideProgressBar()
                    viewContract?.onErrorReceived(getError(it))
                })!!
        )
    }

    fun filterLaunchesByTBD() {
        launchesList.value = launchesList.value?.filter { it.tbd }
    }

    fun fetchOriginalLaunches() {
        launchesList.value = originalList
    }

}