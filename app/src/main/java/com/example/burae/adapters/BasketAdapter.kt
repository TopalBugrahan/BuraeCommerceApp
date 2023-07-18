package com.example.burae.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.burae.R
import com.example.burae.di.basketDao.RoomProductData
import com.example.burae.models.Product

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.MyCostumeViewHolder>() {

    private var roomProductData: List<RoomProductData>? = null

    fun setData(roomProductData: List<RoomProductData>) {
        Log.d("veri",roomProductData.toString())
        this.roomProductData = roomProductData
        notifyDataSetChanged()
    }

    class MyCostumeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.baketImage)
        val title = view.findViewById<TextView>(R.id.basketTitle)
        val count = view.findViewById<EditText>(R.id.basketCount)
        val totalPrice= view.findViewById<TextView>(R.id.basketPrice)

        fun bind(data: RoomProductData){
            title.text=data.title
            count.setText(data.productCount.toString())
            totalPrice.text="100"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCostumeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        return BasketAdapter.MyCostumeViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (roomProductData == null)
            return 0
        else
            return roomProductData!!.size
    }

    override fun onBindViewHolder(holder: MyCostumeViewHolder, position: Int) {
        holder.bind(roomProductData!!.get(position))
    }
}