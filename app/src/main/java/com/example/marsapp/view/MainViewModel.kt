package com.example.marsapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsapp.data.MarsResponseItem
import com.example.marsapp.repo.MarsPropertyRepository

class MainViewModel(private val marsPropertyRepository: MarsPropertyRepository) : ViewModel() {

    private val _properties = MutableLiveData<List<MarsResponseItem>>()
    val properties: LiveData<List<MarsResponseItem>> = _properties

    init {
        fetchProperties()

    }
    private fun fetchProperties() {
        marsPropertyRepository.fetchProperties { properties ->
            _properties.postValue(properties)
        }
    }
}

