package com.example.greedy.adapter

import android.view.View
import android.view.ViewParent
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.example.greedy.databinding.TagsViewBinding

class TagsViewHolder(itemView: View, tagsViewBinding: TagsViewBinding) : RecyclerView.ViewHolder(itemView) {
lateinit var tagsViewBinding:TagsViewBinding
lateinit var parent:  View
init {
    this.tagsViewBinding=tagsViewBinding
    this.parent=itemView
}
}