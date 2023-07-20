package com.example.burae.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.burae.R
import com.example.burae.di.basketDao.BasketTotalData
import com.example.burae.di.basketDao.RoomProductData
import com.example.burae.interfaces.BasketListSelectListener
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.Product

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.MyCostumeViewHolder>() {

    private var roomProductData: List<BasketTotalData>? = null
    private lateinit var listener: BasketListSelectListener

    fun setData(roomProductData: List<BasketTotalData>) {
        Log.d("veri",roomProductData.toString())
        this.roomProductData = roomProductData
        notifyDataSetChanged()
    }

    fun setListener(listener: BasketListSelectListener){
        this.listener=listener
    }


    class MyCostumeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.baketImage)
        val title = view.findViewById<TextView>(R.id.basketTitle)
        val count = view.findViewById<TextView>(R.id.basketCount)
        val totalPrice= view.findViewById<TextView>(R.id.basketPrice)
        val btnIncrease= view.findViewById<Button>(R.id.btnIncrease)
        val btnDecrease=view.findViewById<Button>(R.id.btnDecrease)
        fun bind(data: BasketTotalData ,listener: BasketListSelectListener){
            btnDecrease.setOnClickListener {
                listener.basketDecreaseItemClick(data)
            }

            btnIncrease.setOnClickListener {
                listener.basketIncreaseItemClick(data)
            }
            Glide.with(image)
                .load(data.imageUrl)
                .into(image)
            title.text=data.title
            count.text=data.toplam.toString()
            totalPrice.text=(data.toplam*data.totalPrice).toString()

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
        holder.bind(roomProductData!!.get(position),listener)
    }
}