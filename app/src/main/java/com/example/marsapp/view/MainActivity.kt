package com.example.marsapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marsapp.R
import com.example.marsapp.data.MarsResponse
import com.example.marsapp.view.adapters.MarsPropertyAdapter


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var marsResponse: MarsResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

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

//        buttonPrice.setOnClickListener {
//            val price = editTextPrice.text.toString().toInt()
//            viewModel.filterWithPrice(price)
//        }

        viewModel.marsServiceCall()

        viewModel.properties.observe(this, Observer { marsResponse ->
            setupRecyclerView(marsResponse)
            this.marsResponse = marsResponse


//            Glide.with(this)
//                .load(marsResponse[0].imgSrc) // image url
//                .centerCrop()
//                .into(imageView);

        })
    }

    private fun setupRecyclerView(marsProperties: MarsResponse) {
        val adapter = MarsPropertyAdapter(marsProperties)
        recyclerView.adapter = adapter
    }
}