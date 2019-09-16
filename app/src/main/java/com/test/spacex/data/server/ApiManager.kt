package com.test.spacex.data.server

import com.test.spacex.data.server.models.LaunchModel
import io.reactivex.Observable

interface ApiManager {
    fun getLaunches(): Observable<List<LaunchModel>?>?
}