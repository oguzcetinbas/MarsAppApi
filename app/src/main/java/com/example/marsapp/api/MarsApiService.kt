package com.example.marsapp.api

import com.example.marsapp.constants.Consts
import com.example.marsapp.data.MarsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApiService {

    @GET("realestate?filter=all")
    fun getProperties(): Call<MarsResponse>

    @GET("realestate")
    fun getPropertiesWithFilter(@Query("filter") filter: String): Call<MarsResponse>

    companion object {

        fun create(): MarsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MarsApiService::class.java)
        }
    }
}