package com.freenow.task.repository

import com.freenow.task.api.ApiService
import javax.inject.Inject

class POIListRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getPoiList() = apiService.getPoiList()
}