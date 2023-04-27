package com.example.marsapp.view


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsapp.api.MarsApiService
import com.example.marsapp.data.MarsResponseItem
import com.example.marsapp.repo.MarsPropertyRepostory
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    private val marsApiService = MarsApiService.create()

    private val _properties = MutableLiveData<List<MarsResponseItem>>()
    val properties: LiveData<List<MarsResponseItem>> = _properties

    init {
        marsServiceCall()
        loadPropertiesFromDataBase()
    }

    private fun loadPropertiesFromDataBase() {
        MarsPropertyRepostory.getAllProperties { properties ->
            _properties.postValue(properties)
        }
    }


    fun getFilterServiceCall(filter: String) {

        val marsPropertiesWithFilterCall = marsApiService.getPropertiesWithFilter(filter)
        marsPropertiesWithFilterCall.enqueue(object : Callback<List<MarsResponseItem>> {
            override fun onResponse(
                call: retrofit2.Call<List<MarsResponseItem>>,
                response: Response<List<MarsResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _properties.value = response.body()
                    response.body()?.let {
                        MarsPropertyRepostory.insertProperties(it) { succes ->
                            if (succes) {
                                Log.e("Database islemi", "Kayit basarili")
                            } else {
                                Log.e("Database islemi", "Hata")
                            }

                        }
                    }
                } else {

                }
            }

            override fun onFailure(call: retrofit2.Call<List<MarsResponseItem>>, t: Throwable) {

            }
        })
    }

    fun marsServiceCall() {
        marsApiService.getProperties().enqueue(object : Callback<List<MarsResponseItem>> {

            override fun onResponse(
                call: retrofit2.Call<List<MarsResponseItem>>,
                response: Response<List<MarsResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _properties.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: retrofit2.Call<List<MarsResponseItem>>, t: Throwable) {

            }
        })

    }

    fun filterWithPrice(price: Int) {

    }
}

