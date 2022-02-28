package com.freenow.task.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.freenow.task.R
import com.freenow.task.adapter.PoiListAdapter
import com.freenow.task.databinding.PoiListFragmentBinding
import com.freenow.task.model.PoiItem
import com.freenow.task.util.InternetConnection
import com.freenow.task.util.Resource
import com.freenow.task.util.Status
import com.freenow.task.viewModel.POIListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class POIListFragment : Fragment() {

    private lateinit var poiListFragmentBinding: PoiListFragmentBinding
    private val poiListViewModel: POIListViewModel by viewModels()
    private lateinit var poiItemList: List<PoiItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        poiListFragmentBinding = PoiListFragmentBinding.inflate(inflater, container, false)
        poiListFragmentBinding.poiListViewModel = poiListViewModel
        loadPoiList()
        return poiListFragmentBinding.root
    }

    /**
     * This fun is for Network connectivity check.
     * If it returns TRUE i.e connection is available then it will API and it will fetch data.
     * Otherwise will throw network error message.
     */
    private fun loadPoiList() {
        if (InternetConnection.checkNetworkConnection(requireContext())) {
            populatePoiList()
        } else {
            poiListFragmentBinding.progressBar.visibility = View.GONE
            Toast.makeText(requireContext(), getString(R.string.noInternet), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun populatePoiList() {
        val bundle = Bundle()
        poiListFragmentBinding.rcvAppliance.apply {
            adapter = PoiListAdapter(object : PoiListAdapter.OnItemClickListener {
                override fun onImageClick(poiItem: PoiItem) {
                    poiItem.selectedPOI = true
                    bundle.putSerializable(getString(R.string.poiData), poiItemList as Serializable)
                    findNavController().navigate(
                        R.id.action_POIListFragment_to_MapFragment,
                        bundle
                    )
                }
            })
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
        poiListViewModel.getPoiList().observe(viewLifecycleOwner) {
            it?.let { resource ->
                if (resource.status == Status.SUCCESS) {
                    poiItemList = resource.data!!
                }
                renderUiState(resource)
            }
        }
    }

    /**
     * Based on api response status UI will be updated.
     */
    private fun renderUiState(resource: Resource<List<PoiItem>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                poiListFragmentBinding.rcvAppliance.visibility = View.VISIBLE
                poiListFragmentBinding.progressBar.visibility = View.GONE
                @Suppress("UNCHECKED_CAST")
                (poiListFragmentBinding.rcvAppliance.adapter as ListAdapter<*, *>).submitList(
                    resource.data as List<Nothing>?
                )
            }
            Status.LOADING -> {
                poiListFragmentBinding.rcvAppliance.visibility = View.GONE
                poiListFragmentBinding.progressBar.visibility = View.VISIBLE
            }
            Status.ERROR -> {
                poiListFragmentBinding.rcvAppliance.visibility = View.VISIBLE
                poiListFragmentBinding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}