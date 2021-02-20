package com.example.greedy.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.greedy.databinding.ImageItemBinding

class GalleryViewHolder(itemView: View,imageItemBinding: ImageItemBinding) :RecyclerView.ViewHolder(itemView) {
    lateinit var imageItemBinding: ImageItemBinding
    init {
        this.imageItemBinding=imageItemBinding
    }
}