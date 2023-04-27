package com.example.marsapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R
import com.example.marsapp.data.MarsResponseItem
import com.example.marsapp.repo.MarsPropertyRepostory
import com.example.marsapp.view.adapters.MarsPropertyAdapter


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MarsPropertyRepostory.initialize(this)

        recyclerView = findViewById(R.id.recycler_view)

        val buttonBuy = findViewById<Button>(R.id.buttonBuy)
        val buttonRent = findViewById<Button>(R.id.buttonRent)
        val buttonPrice = findViewById<Button>(R.id.buttonprice)
        val editTextPrice = findViewById<EditText>(R.id.editTextPrice)


        buttonBuy.setOnClickListener {
            viewModel.getFilterServiceCall("buy")
        }

        buttonRent.setOnClickListener {
            viewModel.getFilterServiceCall("rent")
        }

        buttonPrice.setOnClickListener {
            val price = editTextPrice.text.toString().toInt()
            viewModel.filterWithPrice(price)
        }

        //viewModel.marsServiceCall()

        viewModel.properties.observe(this, Observer { marsResponse ->
            setupRecyclerView(marsResponse)
            /*
                Log.e("MainActivityServistenGelen", marsResponse.toString())
                Glide.with(this)
                    .load(marsResponse[0].imgSrc) // image url
                    .centerCrop()
                    .into(imageView)
            */

        })
    }

    private fun setupRecyclerView(marsProperties: List<MarsResponseItem>) {
        val adapter = MarsPropertyAdapter(marsProperties)
        recyclerView.adapter = adapter
    }
}