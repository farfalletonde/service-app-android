package com.example.casestudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casestudy.R
import com.example.casestudy.model.homePageModel.Service

class PopularServicesAdapter(
    private val context: Context,
    private val dataset: List<Service>
    ): RecyclerView.Adapter<PopularServicesAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val serviceImage: ImageView = view.findViewById(R.id.popularServiceItemImage)
        val serviceName: TextView = view.findViewById(R.id.popularServiceItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_popular, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]

        holder.apply {
            Glide.with(context).load(item.image_url).into(serviceImage)
            serviceName.text = item.long_name
        }
    }

    override fun getItemCount() = dataset.size
}