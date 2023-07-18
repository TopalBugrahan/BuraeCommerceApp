package com.example.burae.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.burae.R
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.Product

class MainListAdapter:RecyclerView.Adapter<MainListAdapter.MyCostumHolder>() {

    private var productList :List<Product>?=null
    private lateinit var listener:MainListSelectListener


    fun setListener(listener: MainListSelectListener){
        this.listener=listener
    }


    fun setList(data:List<Product>){
        this.productList=data
        notifyDataSetChanged()
    }

    class MyCostumHolder (val view:View):RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.item_image)
        val itemName = view.findViewById<TextView>(R.id.item_name)
        val itemPrice = view.findViewById<TextView>(R.id.item_price)
        val imageButton=view.findViewById<ImageButton>(R.id.btnBasket)

        @SuppressLint("SetTextI18n")
        fun bind(data:Product,listener:MainListSelectListener){
            imageButton.setOnClickListener {
                listener.onItemClick(data)
            }
            itemName.text=data.title
            itemPrice.text="${data.price} TL"
            Glide.with(image)
                .load(data.images.get(0))
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCostumHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item,parent,false)
        return MyCostumHolder(view)
    }

    override fun getItemCount(): Int {
        if(productList==null){
            return 0
        }
        else
            return productList!!.size

    }

    override fun onBindViewHolder(holder: MyCostumHolder, position: Int) {
        holder.bind(productList!!.get(position),listener)

    }
}