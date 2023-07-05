package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.DataFood
import com.example.myapplication.FoodDisplayAct
import com.example.myapplication.R

class AdapterFood(private val context: Context) : RecyclerView.Adapter<AdapterFood.ItemHolder>() {

    val foodList = ArrayList<DataFood>()

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvFoodName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvFoodDescription: TextView = itemView.findViewById(R.id.tvFoodDescription)
        val llFood: LinearLayout = itemView.findViewById(R.id.llFood)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(context).inflate(R.layout.rcv_food_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.tvFoodName.setText(foodList.get(position).foodName)
        holder.tvFoodDescription.setText(foodList.get(position).foodDescription)

        holder.llFood.setOnClickListener {
            context.startActivity(
                Intent(context, FoodDisplayAct::class.java).putExtra(
                    "foodName",
                    foodList.get(position).foodName
                )
                    .putExtra("foodDescription", foodList.get(position).foodDescription).putExtra("id",foodList.get(position).id)
            )
        }

    }

    fun setData(list: List<DataFood>) {
        foodList.clear()
        foodList.addAll(list)
        notifyDataSetChanged()
    }
}