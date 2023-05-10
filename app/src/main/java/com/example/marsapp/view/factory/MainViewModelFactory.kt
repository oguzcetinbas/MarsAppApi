package com.example.marsapp.view.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marsapp.repo.MarsPropertyRepository
import com.example.marsapp.view.MainViewModel

class MainViewModelFactory(private val marsPropertyRepository: MarsPropertyRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        if (viewModelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(marsPropertyRepository) as T
        }
        throw IllegalArgumentException("Unknow View Model class ")
    }
}