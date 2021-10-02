package com.example.casestudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.casestudy.R
import com.example.casestudy.model.homePageModel.Service

class AllServicesAdapter(
    private val dataset: List<Service>
    ): RecyclerView.Adapter<AllServicesAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val serviceName : TextView = view.findViewById(R.id.serviceListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_services, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]


        val icon = when (item.name) {

            "Tadilat" -> R.drawable.ic_tadilat
            "Temizlik" -> R.drawable.ic_temizlik
            "Nakliyat" -> R.drawable.ic_nakliyat
            "Tamir" -> R.drawable.ic_tamir
            "Özel Ders" -> R.drawable.ic_ozel_ders
            "Sağlık Eğitimi" -> R.drawable.ic_saglik
            "Düğün Organizasyon" -> R.drawable.ic_dugun
            else -> R.drawable.ic_diger

        }


        holder.serviceName.apply {
            text = item.name
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, icon, 0, 0)
        }

    }

    override fun getItemCount() = dataset.size
}