package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.Adapter.AdapterFood
import com.example.myapplication.Data.DataFood
import com.example.myapplication.Data.FoodDatabase

class MainActivity : AppCompatActivity() {

    lateinit var ivBtnAdd: ImageView
    lateinit var rcvFood: RecyclerView
    lateinit var mAdapterFood: AdapterFood
    lateinit var database: FoodDatabase
    var foodList = listOf<DataFood>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        bindClicks()
        updateRcv()
    }

    fun updateRcv() {
        Thread {
            foodList = database.foodDao().getAllFood()
            runOnUiThread {
                mAdapterFood.setData(foodList)
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        updateRcv()
    }

    fun bindViews() {
        ivBtnAdd = findViewById(R.id.ivBtnAdd)
        rcvFood = findViewById(R.id.rcvFood)

        mAdapterFood = AdapterFood(this)
        rcvFood.layoutManager = GridLayoutManager(this, 1)
        rcvFood.adapter = mAdapterFood

        database =
            Room.databaseBuilder(applicationContext, FoodDatabase::class.java, "foodDB").build()

    }

    fun bindClicks() {

        ivBtnAdd.setOnClickListener {
            startActivity(Intent(this, FoodAddAct::class.java))
        }
    }
}