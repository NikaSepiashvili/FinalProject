package com.example.project.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Session::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun SessionDao(): SessionDao
}