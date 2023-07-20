package com.example.burae.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burae.R
import com.example.burae.interfaces.MainListSelectListener
import com.example.burae.models.Category
import com.example.burae.models.Product


class CategoryExpandableListAdapter(private val context: Context, private val categoryList: List<Category>,private val listener:MainListSelectListener) : BaseExpandableListAdapter() {

    private val productsMap: MutableMap<String, List<Product>> = mutableMapOf()

    override fun getGroupCount(): Int {
        return categoryList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {

        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return categoryList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        val categoryName = categoryList[groupPosition].categoryName
        return productsMap[categoryName]?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val groupViewHolder: GroupViewHolder
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.category_item_parent, parent, false)
            groupViewHolder = GroupViewHolder(view)
            view.tag = groupViewHolder
        } else {
            groupViewHolder = view.tag as GroupViewHolder
        }
        val category = categoryList[groupPosition]
        groupViewHolder.bind(category)
        return view!!
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val childViewHolder: ChildViewHolder
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.category_item_child, parent, false)
            childViewHolder = ChildViewHolder(view)
            view.tag = childViewHolder
        } else {
            childViewHolder = view.tag as ChildViewHolder
        }
        val categoryName = categoryList[groupPosition].categoryName
        val products = productsMap[categoryName]
        if (products != null) {
            childViewHolder.bind(products)
        }
        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    fun updateProductsMap(productsMap: Map<String, List<Product>>) {
        //Log.d("map",this.productsMap.toString())
        this.productsMap.clear()
        this.productsMap.putAll(productsMap)
        //Log.d("mapppp",this.productsMap.toString())
        notifyDataSetChanged()
    }

    private inner class GroupViewHolder(itemView: View) {
        private val categoryNameTextView: TextView = itemView.findViewById(R.id.parentName)

        fun bind(category: Category) {
            categoryNameTextView.text = category.categoryName
        }
    }

    private inner class ChildViewHolder(itemView: View) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)
        private lateinit var productAdapter: MainListAdapter

        fun bind(products: List<Product>) {
            //Log.d("products",productsMap.toString())
            productAdapter = MainListAdapter()
            val spanCount = 2
            val layoutManager = GridLayoutManager(context, spanCount)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = layoutManager
            productAdapter.setList(products)
            recyclerView.adapter = productAdapter
            productAdapter.setListener(listener)
        }
    }
}
