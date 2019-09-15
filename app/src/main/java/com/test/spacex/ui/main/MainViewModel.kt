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
) : BaseViewModel(dataManager, schedulerProvider, compositeDisposable) {

    val launchesList = MutableLiveData<List<LaunchModel>>()

    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    var originalList: List<LaunchModel>? = null

    fun getLaunches() {
        isLoading.value = true
        compositeDisposable.add(
            dataManager.getLaunches()
                ?.subscribeOn(schedulerProvider.io())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe({
                    isLoading.value = false
                    originalList = it
                    launchesList.value = originalList
                }, {
                    isLoading.value = false
                    error.value = getError(it)
                })!!
        )
    }

    fun filterLaunchesByTBD() {
        launchesList.value = launchesList.value?.filter { it.tbd ?: false }
    }

    fun fetchOriginalLaunches() {
        launchesList.value = originalList
    }

}