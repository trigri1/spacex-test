package com.test.spacex.data.server

import com.test.spacex.data.server.models.LaunchModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiClient {

    @GET("/v3/launches")
    fun getLaunches(): Observable<List<LaunchModel>>?
}