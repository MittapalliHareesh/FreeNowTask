package com.freenow.task.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.freenow.task.repository.POIListRepository
import com.freenow.task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

/**
 * @HiltViewModel Indicates DI for ViewModel. All Hilt ViewModels are provided by the
 * ViewModelComponent which follows the same lifecycle as a ViewModel.
 */
@HiltViewModel
class POIListViewModel @Inject constructor(private val repository: POIListRepository) :
    ViewModel() {

    /**
     * It initiate call to APIService class to fetch response.
     * Response status either be any one the states SUCCESS or ERROR or LOADING. Based state data
     * will be populated to View.
     */
    fun getPoiList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPoiList().poiList))
        } catch (httpException: HttpException) {
            emit(
                Resource.error(
                    data = null,
                    message = getErrorMessage(httpException.code())
                )
            )
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred while fetching data!"
                )
            )
        }
    }

    private fun getErrorMessage(httpCode: Int): String {
        return when (httpCode) {
            401 -> "Accessing Unauthorised request"
            404 -> "Not Found.\n\nPlease validate the requested URL."
            500 -> "Server not found Error"
            else -> "Something went wrong!!"
        }
    }
}