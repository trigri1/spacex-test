package com.test.spacex.data

import com.test.spacex.data.prefs.PrefsManager
import com.test.spacex.data.server.ApiManager
import com.test.spacex.data.server.models.LaunchModel
import io.reactivex.Observable
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
    val apiManager: ApiManager?,
    val prefsManager: PrefsManager?
) : DataManager {


    override fun getLaunches(): Observable<List<LaunchModel>?>? {
        return apiManager?.getLaunches()
    }

}