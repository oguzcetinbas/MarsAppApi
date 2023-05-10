package com.example.marsapp.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R
import com.example.marsapp.data.MarsResponseItem
import com.example.marsapp.repo.MarsPropertyRepository
import com.example.marsapp.view.adapters.MarsPropertyAdapter
import com.example.marsapp.view.factory.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marsPropertyRepository = MarsPropertyRepository(this)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(marsPropertyRepository)
        )[MainViewModel::class.java]

        recyclerView = findViewById(R.id.recycler_view)

        viewModel.properties.observe(this, Observer { marsResponse ->
            setupRecyclerView(marsResponse)
        })
    }

    private fun setupRecyclerView(marsProperties: List<MarsResponseItem>) {
        val adapter = MarsPropertyAdapter(marsProperties)
        recyclerView.adapter = adapter
    }
}