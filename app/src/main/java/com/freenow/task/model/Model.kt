package com.freenow.task.model

import java.io.Serializable

data class ResponseData(
    val poiList: List<PoiItem>
)

/**
 * To move data objects b/w fragments,It should be serializable. Hence extending data class with
 * Serializable interface.
 */

data class PoiItem(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Double,
    var selectedPOI: Boolean = false
) : Serializable

data class Coordinate(
    val latitude: Double,
    val longitude: Double
) : Serializable