package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.room.Room
import com.example.myapplication.Data.DataFood
import com.example.myapplication.Data.FoodDatabase

class FoodAddAct : AppCompatActivity() {

    lateinit var ivFoodImage: ImageView
    lateinit var edtFoodName: EditText
    lateinit var edtFoodDescription: EditText
    lateinit var btnAddFood: Button
    lateinit var database: FoodDatabase
    val foodList = listOf<DataFood>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_add)

        bindViews()
        bindClicks()
    }

    fun bindViews() {
        ivFoodImage = findViewById(R.id.ivFoodImage)
        edtFoodName = findViewById(R.id.edtFoodName)
        edtFoodDescription = findViewById(R.id.edtFoodDescription)
        btnAddFood = findViewById(R.id.btnAddFood)

        database =
            Room.databaseBuilder(applicationContext, FoodDatabase::class.java, "foodDB")
                .build()
    }

    fun bindClicks() {

        btnAddFood.setOnClickListener {

            val foodName = edtFoodName.text.toString()
            val foodDescription = edtFoodDescription.text.toString()

            Thread {
                database.foodDao().insertFood(DataFood(0, foodName, foodDescription))
                runOnUiThread {
                    finish()
                }
            }.start()
        }
    }
}