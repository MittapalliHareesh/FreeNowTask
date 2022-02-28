package com.freenow.task.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freenow.task.repository.POIListRepository
import com.freenow.task.util.MockResponseFileReader
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GetPOIListApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val server = MockWebServer()

    private lateinit var repository: POIListRepository

    private lateinit var mockedResponse: String

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun setUp() {
        server.start(8000)

        val BASE_URL = server.url("/").toString()

        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

        repository = POIListRepository(service)
    }

    @Test
    fun testApiSuccess() {
        mockedResponse = MockResponseFileReader("success.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { repository.apiService.getPoiList() }
        val json = gson.toJson(response)

        val resultResponse = JsonParser.parseString(json)
        val expectedResponse = JsonParser.parseString(mockedResponse)

        Assert.assertNotNull(response)
        Assert.assertTrue(
            resultResponse.asJsonObject.get("poiList").asJsonArray.size() ==
                    expectedResponse.asJsonObject.get("poiList").asJsonArray.size()
        )
    }

    @Test
    fun testApiError() {
        mockedResponse = MockResponseFileReader("error.json").content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { repository.apiService.getPoiList() }
        Assert.assertNotNull(response)
        Assert.assertNull(response.poiList)
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}