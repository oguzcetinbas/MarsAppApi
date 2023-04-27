package com.example.marsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mars_property")
data class MarsResponseItem(

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("img_src")
    val imgSrc: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("type")
    val type: String
)