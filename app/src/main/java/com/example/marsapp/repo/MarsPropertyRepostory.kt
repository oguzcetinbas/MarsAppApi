package com.example.marsapp.repo

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.marsapp.api.MarsApiService
import com.example.marsapp.data.MarsResponseItem
import com.example.marsapp.db.MarsPropertyDao
import com.example.marsapp.db.MarsPropertyDatabase
import com.example.marsapp.util.NetworkUtil
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MarsPropertyRepository(private val context: Context) {

    private val marsApiService = MarsApiService.create()
    private lateinit var marsPropertyDao: MarsPropertyDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init {

        val marsPropertyDatabase = Room.databaseBuilder(
            context.applicationContext,
            MarsPropertyDatabase::class.java,
            "name_property_database"
        ).build()

        marsPropertyDao = marsPropertyDatabase.marsPropertyDao()
    }

    fun fetchProperties(callback: (properties: List<MarsResponseItem>) -> Unit) {
        if (NetworkUtil.isInternetAvailable(context)) {
            fetchFromService(callback)
            println("Gelen data servisten")
        }else {
            fetchFromDatabase(callback)
            println("gelen data database'den ")
        }
    }

    private fun fetchFromService(callback: (properties: List<MarsResponseItem>) -> Unit) {
        marsApiService.getProperties().enqueue(object : Callback<List<MarsResponseItem>> {
            override fun onResponse(
                call: retrofit2.Call<List<MarsResponseItem>>,
                response: Response<List<MarsResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responceList = response.body()
                    if (responceList != null) {
                        callback(responceList)
                        insertProperties(responceList)
                    }

                } else {
                    fetchFromDatabase(callback)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<MarsResponseItem>>, t: Throwable) {
                fetchFromDatabase(callback)
            }
        })

    }

    fun fetchFromDatabase(callback: (properties: List<MarsResponseItem>) -> Unit) {
        executor.execute {
            val properties = marsPropertyDao.getAllProperties()
            callback(properties)
        }
    }

    fun insertProperties(properties: List<MarsResponseItem>) {
        executor.execute {
            try {
                marsPropertyDao.insertProperties(properties)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}