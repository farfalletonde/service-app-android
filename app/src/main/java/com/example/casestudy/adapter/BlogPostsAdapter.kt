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
import com.example.casestudy.model.homePageModel.Post

class BlogPostsAdapter(
    private val context: Context,
    private val dataset: List<Post>
    ): RecyclerView.Adapter<BlogPostsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val blogImage: ImageView = view.findViewById(R.id.blogItemImage)
        val blogCategory: TextView = view.findViewById(R.id.blogItemCategory)
        val blogTitle: TextView = view.findViewById(R.id.blogItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_blog, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]
        holder.apply {
            Glide.with(context).load(item.image_url).into(blogImage)
            blogCategory.text = item.category
            blogTitle.text = item.title
        }
    }

    override fun getItemCount() = dataset.size
}