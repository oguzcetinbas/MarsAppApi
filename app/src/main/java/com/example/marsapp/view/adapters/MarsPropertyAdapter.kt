package com.example.marsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsapp.R
import com.example.marsapp.data.MarsResponseItem

class MarsPropertyAdapter(private val marsProperties: List<MarsResponseItem>) :
    RecyclerView.Adapter<MarsPropertyAdapter.MarsPropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.property_item, parent, false)
        return MarsPropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        holder.bind(marsProperties[position])
    }

    override fun getItemCount() = marsProperties.size


    class MarsPropertyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val propertyImage: ImageView = view.findViewById(R.id.property_image)
        private val propertyType: TextView = view.findViewById(R.id.property_type)
        private val propertyPrice: TextView = view.findViewById(R.id.property_price)

        fun bind(marsProperty: MarsResponseItem) {
            Glide.with(view.context).load(marsProperty.imgSrc).into(propertyImage)
            propertyType.text = marsProperty.type.capitalize()
            propertyPrice.text = "Price: ${marsProperty.price}"
        }
    }
}