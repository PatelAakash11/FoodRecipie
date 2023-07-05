package com.example.myapplication.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDAO {

    @Insert
    fun insertFood(dataFood: DataFood)

    @Update
    fun updateFood(dataFood: DataFood)

    @Delete
    fun deleteFood(dataFood: DataFood)

    @Query("SELECT * FROM DATAFOOD")
    fun getAllFood(): List<DataFood>

}