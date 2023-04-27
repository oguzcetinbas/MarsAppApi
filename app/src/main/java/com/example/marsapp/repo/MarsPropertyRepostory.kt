package com.example.marsapp.repo

import android.content.Context
import androidx.room.Room
import com.example.marsapp.data.MarsResponseItem
import com.example.marsapp.db.MarsPropertyDao
import com.example.marsapp.db.MarsPropertyDatabase
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object MarsPropertyRepostory {

    private lateinit var marsPropertyDao: MarsPropertyDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun initialize(context: Context) {

        val marsPropertyDatabase = Room.databaseBuilder(
            context.applicationContext,
            MarsPropertyDatabase::class.java,
            "name_property_database"
        ).build()

        marsPropertyDao = marsPropertyDatabase.marsPropertyDao()
    }

    fun insertProperties(properties: List<MarsResponseItem>, callback: (succes: Boolean) -> Unit) {

        executor.execute {
            try {
                marsPropertyDao.insertProperties(properties)
                callback(true)
            } catch (e: Exception) {
                e.printStackTrace()
                callback(false)
            }
        }
    }

    fun getAllProperties(callback: (properties: List<MarsResponseItem>) -> Unit) {
        executor.execute {
            val properties = marsPropertyDao.getAllProperties()
            callback(properties)
        }
    }
}