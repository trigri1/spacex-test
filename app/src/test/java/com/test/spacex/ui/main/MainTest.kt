package com.test.spacex.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.whenever
import com.test.spacex.data.DataManager
import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.rx.SchedulerProvider
import com.test.spacex.rx.TrampolineSchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class MainTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var dataManager: DataManager

    @Mock
    lateinit var viewContract: MainContract

    @Mock
    lateinit var observer: Observer<List<LaunchModel>>

    private lateinit var schedulerProvider: SchedulerProvider

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        compositeDisposable = CompositeDisposable()
        schedulerProvider = TrampolineSchedulerProvider()

        mainViewModel = MainViewModel(dataManager, schedulerProvider, compositeDisposable)
        mainViewModel.viewContract = viewContract
    }

    @Test
    fun getLaunchesTest() {
        val list =
            mutableListOf(getMockedLaunch(true), getMockedLaunch(true), getMockedLaunch(true))
        whenever(dataManager.getLaunches()).thenReturn(Observable.just(list))

        mainViewModel.launchesList.observeForever(observer)
        mainViewModel.getLaunches()

        assert(list.size == mainViewModel.launchesList.value!!.size)

        inOrder(viewContract) {
            viewContract.showProgressBar()
            viewContract.hideProgressBar()
        }

    }

    @Test
    fun testLaunchesFilter() {

        val list =
            mutableListOf(getMockedLaunch(true), getMockedLaunch(false), getMockedLaunch(true))
        mainViewModel.launchesList.value = list
        mainViewModel.originalList = list

        mainViewModel.launchesList.observeForever(observer)
        mainViewModel.filterLaunchesByTBD()

        assert(2 == mainViewModel.launchesList.value!!.size)
    }


    private fun getMockedLaunch(tbd: Boolean): LaunchModel {
        return LaunchModel(
            11.0, "hello", listOf("1234"),
            0.0, 0.0, "",
            "", false, "",
            tbd, 0.0, listOf("ship"), false, "",
            false, "", 0.0
        )
    }


}