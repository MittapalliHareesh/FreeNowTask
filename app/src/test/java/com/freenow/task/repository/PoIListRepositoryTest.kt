package com.freenow.task.repository

import com.freenow.task.api.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class PoIListRepositoryTest {

    private lateinit var repository: POIListRepository

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        repository = POIListRepository(apiService)
    }

    @Test
    fun `whenever we call getPoiList API, it should call getPoiList fun in ApiService`() {
        runBlocking {
            repository.getPoiList()
            verify(apiService).getPoiList()
        }
    }
}