package com.example.marsapp.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsapp.api.MarsApiService
import com.example.marsapp.data.MarsResponse
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    private val marsApiService = MarsApiService.create()

    private val _properties = MutableLiveData<MarsResponse>()
    val properties: LiveData<MarsResponse> = _properties

    /*   init {
           marsServiceCall()
       }
   */

    fun getFilterServiceCall(filter: String) {

        val marsPropertiesWithFilterCall = marsApiService.getPropertiesWithFilter(filter)
        marsPropertiesWithFilterCall.enqueue(object : Callback<MarsResponse> {
            override fun onResponse(
                call: retrofit2.Call<MarsResponse>,
                response: Response<MarsResponse>
            ) {
                if (response.isSuccessful) {
                    _properties.value?.clear()
                    _properties.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: retrofit2.Call<MarsResponse>, t: Throwable) {

            }
        })
    }

    fun marsServiceCall() {
        marsApiService.getProperties().enqueue(object : Callback<MarsResponse> {

            override fun onResponse(
                call: retrofit2.Call<MarsResponse>,
                response: Response<MarsResponse>
            ) {
                if (response.isSuccessful) {
                    _properties.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: retrofit2.Call<MarsResponse>, t: Throwable) {

            }
        })

    }
}

