package com.freenow.task.util

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.freenow.task.R
import com.freenow.task.model.PoiItem
import kotlin.math.floor

@BindingAdapter("taxiImage")
fun AppCompatImageView.setTaxiImage(poiItem: PoiItem) {

    when (poiItem.fleetType) {
        context.getString(R.string.taxi) -> {
            setImageResource(R.mipmap.ic_taxi)
        }
        context.getString(R.string.taxi_pooling) -> {
            setImageResource(R.mipmap.ic_taxipool)
        }
        else -> {
            setImageResource(R.mipmap.ic_launcher)
        }
    }
}

@BindingAdapter("idValue")
fun TextView.setIdValue(poiItem: PoiItem) {
    text = poiItem.id.toString()
}

@BindingAdapter("fleetTypeValue")
fun TextView.setFleetTypeValue(poiItem: PoiItem) {
    text = poiItem.fleetType
}

@BindingAdapter("headingValue")
fun TextView.setHeadingValue(poiItem: PoiItem) {
    text = String.format("%.3f", poiItem.heading)
}