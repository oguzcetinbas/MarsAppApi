package com.example.marsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marsapp.data.MarsResponseItem


@Database(entities = [MarsResponseItem::class], version = 1)
abstract class MarsPropertyDatabase : RoomDatabase() {

    abstract fun marsPropertyDao(): MarsPropertyDao

}