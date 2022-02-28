package com.freenow.task.api

import com.freenow.task.model.ResponseData
import retrofit2.http.GET

interface ApiService {

    @GET("?p1Lat=53.694865&p1Lon=9.757589&p2Lat=53.394655&p2Lon=10.099891")
    suspend fun getPoiList(): ResponseData
}