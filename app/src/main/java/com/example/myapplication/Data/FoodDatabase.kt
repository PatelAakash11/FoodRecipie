package com.example.myapplication.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataFood::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDAO
}