package com.test.spacex.data.server

import com.test.spacex.data.server.models.LaunchModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class ApiManagerImpl @Inject constructor(@Named("non_cached") val apiClient: ApiClient?) :
    ApiManager {

    override fun getLaunches(): Observable<List<LaunchModel>?>? {
        return apiClient?.getLaunches()
    }
}