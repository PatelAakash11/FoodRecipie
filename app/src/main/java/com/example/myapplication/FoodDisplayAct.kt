package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import android.widget.TextView
import androidx.room.Room
import com.example.myapplication.Adapter.AdapterFood
import com.example.myapplication.Data.DataFood
import com.example.myapplication.Data.FoodDatabase
import org.w3c.dom.Text

class FoodDisplayAct : AppCompatActivity() {

    lateinit var ivFoodImage: ImageView
    lateinit var tvFoodDescription: TextView
    lateinit var tvFoodName: TextView
    lateinit var ivFavorite: ImageView
    lateinit var ivDelete: ImageView
    lateinit var ivShare: ImageView
    lateinit var mAdapterFood: AdapterFood
    lateinit var database: FoodDatabase
    var foodList = listOf<DataFood>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_display)

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

        ivFoodImage = findViewById(R.id.ivFoodImage)
        tvFoodName = findViewById(R.id.tvFoodName)
        tvFoodDescription = findViewById(R.id.tvFoodDescription)
        ivFavorite = findViewById(R.id.ivFavorite)
        ivDelete = findViewById(R.id.ivDelete)
        ivShare = findViewById(R.id.ivShare)

        val foodName = intent.extras?.getString("foodName")
        val foodDescription = intent.extras?.getString("foodDescription")

        database =
            Room.databaseBuilder(applicationContext, FoodDatabase::class.java, "foodDB").build()

        tvFoodName.setText(foodName)
        tvFoodDescription.setText(foodDescription)

        mAdapterFood = AdapterFood(this)

    }

    fun bindClicks() {

        ivDelete.setOnClickListener {
            val foodName = intent.extras?.getString("foodName")
            val foodDescription = intent.extras?.getString("foodDescription")
            val id = intent.extras?.getLong("id")
            Thread {
                database.foodDao().deleteFood(DataFood(id!!, foodName!!, foodDescription!!))
                runOnUiThread {
                    finish()
                }
            }.start()

        }
        ivShare.setOnClickListener {
            val foodName = intent.extras?.getString("foodName")
            val foodDescription = intent.extras?.getString("foodDescription")

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra("Share this", foodName)
            val chooser = Intent.createChooser(intent, "share using...")
            startActivity(chooser)
        }
    }
}