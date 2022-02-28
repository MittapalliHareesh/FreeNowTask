package com.freenow.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.freenow.task.R
import com.freenow.task.databinding.MapFragmentBinding
import com.freenow.task.model.PoiItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragmentBinding: MapFragmentBinding
    private lateinit var selectedPoi: PoiItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mapFragmentBinding = MapFragmentBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        selectedPoi = arguments?.getSerializable(getString(R.string.selectedPoi)) as PoiItem
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
     * 1: World
    5: Landmass/continent
    10: City
    15: Streets
    20: Buildings
     * */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val selectedLatLng =
            LatLng(selectedPoi.coordinate.latitude, selectedPoi.coordinate.longitude)
        mMap.addMarker(MarkerOptions().position(selectedLatLng))
        val cameraPosition = CameraPosition.Builder().target(selectedLatLng).zoom(10.0f).build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap.moveCamera(cameraUpdate)
    }
}