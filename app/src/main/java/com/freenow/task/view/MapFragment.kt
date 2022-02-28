package com.freenow.task.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.freenow.task.R
import com.freenow.task.databinding.MapFragmentBinding
import com.freenow.task.model.PoiItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragmentBinding: MapFragmentBinding
    private lateinit var poiItemList: List<PoiItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mapFragmentBinding = MapFragmentBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        @Suppress("UNCHECKED_CAST")
        poiItemList = arguments?.getSerializable(getString(R.string.poiData)) as List<PoiItem>
        return mapFragmentBinding.root
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    /**
     * This fun will add markers in Google Map. For Markers we get lat,lng from fake-poi-api.
     * Selected LatLng highlighted with custom icon & set zoom as 10.0f. There are different types
     * of zoom levels available.
     * 1: World ;  5: Landmass/continent ;  10: City;  15: Streets;  20: Buildings
     * MoveCamera will move to selected POI.
     *
     * @param googleMap to indicate all marker options.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        for (poi in poiItemList) {
            val selectedLatLng = LatLng(poi.coordinate.latitude, poi.coordinate.longitude)
            if (poi.selectedPOI) {
                mMap.addMarker(
                    MarkerOptions().position(selectedLatLng).icon(
                        bitmapDescriptorFromVector(
                            requireContext(),
                            R.drawable.ic_map_icon
                        )
                    )
                )
                val cameraPosition =
                    CameraPosition.Builder().target(selectedLatLng).zoom(10.0f).build()
                val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                mMap.moveCamera(cameraUpdate)
            } else {
                mMap.addMarker(MarkerOptions().position(selectedLatLng))
            }
        }
    }

    /**
     * This fun does customizing height/width of custom marker icon & it returns BitmapDescriptor.
     *
     * @param context It's activity / application context.
     * @param vectorResId Indicates custom marker icon.
     */
    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, 70, 200)
            val bitmap =
                Bitmap.createBitmap(70, 200, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}