package com.freenow.task.model

import java.io.Serializable

data class ResponseData(
    val poiList: List<PoiItem>
)

data class PoiItem(
    val id: Long,
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Double
) : Serializable

data class Coordinate(
    val latitude: Double,
    val longitude: Double
) : Serializable