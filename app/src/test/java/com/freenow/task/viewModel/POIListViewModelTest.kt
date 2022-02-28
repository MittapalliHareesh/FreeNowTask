package com.freenow.task.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.freenow.task.model.PoiItem
import com.freenow.task.model.ResponseData
import com.freenow.task.repository.POIListRepository
import com.freenow.task.util.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.timeout
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class POIListViewModelTest {

    private lateinit var poiListViewModel: POIListViewModel

    @Mock
    private lateinit var poiListRepository: POIListRepository

    @Mock
    private lateinit var poiItemObserver: Observer<Resource<List<PoiItem>>>

    @Mock
    private lateinit var responseData: ResponseData

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        runBlocking {
            whenever(poiListRepository.getPoiList()).thenReturn(responseData)
        }
        poiListViewModel = POIListViewModel(poiListRepository)
    }


    @Test
    fun `when getPoiList is called , then observer is updated with success`() {
        runBlocking {
            poiListViewModel.getPoiList().observeForever(poiItemObserver)
            delay(10)
            verify(poiItemObserver, timeout(50)).onChanged(Resource.loading(null))
            verify(poiItemObserver, timeout(50)).onChanged(Resource.success(responseData.poiList))
        }
    }


    @Test
    fun `when getPoiList is called , then observer is updated without any response`() {
        runBlocking {
            poiListViewModel.getPoiList().observeForever(poiItemObserver)
            delay(10)
            verify(poiItemObserver, timeout(50)).onChanged(Resource.loading(null))
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}