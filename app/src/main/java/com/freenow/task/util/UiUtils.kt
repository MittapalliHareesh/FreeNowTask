package com.freenow.task.util

import android.content.Context
import android.location.Geocoder
import kotlinx.coroutines.runBlocking
import java.util.*

object UiUtils {
    const val baseUrl = "https://fake-poi-api.mytaxi.com/"

    //TODO HAreesh, If not using remove it.
    fun fetchAddressFromLocation(context: Context, latitude: Double, longitude: Double) {
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val address = geocoder.getFromLocation(latitude, longitude, 1)
                address?.let {
                    print(address)
                }
            } catch (exception: Exception) {
                print(exception.stackTrace)
            }
    }
}