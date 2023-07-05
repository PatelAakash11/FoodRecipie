package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DataFood(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val foodName: String,
    val foodDescription: String
) : Serializable
