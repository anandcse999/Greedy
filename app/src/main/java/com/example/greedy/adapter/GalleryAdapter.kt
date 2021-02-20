package com.example.greedy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.baselibrary.models.TagsList
import com.example.greedy.R
import com.example.greedy.databinding.ImageItemBinding

class GalleryAdapter(
    val mContext: Context,
    val imageList: List<TagsList>,
) :
    RecyclerView.Adapter<GalleryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val galleryViewItem = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.image_item, parent, false
        ) as ImageItemBinding
        return GalleryViewHolder(galleryViewItem.root, galleryViewItem)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val imageProperties = imageList!!.get(position)
        holder.imageItemBinding.detail=imageProperties
        holder.imageItemBinding.executePendingBindings()
        holder.itemView.setTag(position)
        Glide.with(mContext)
            .load(imageProperties!!.url)
            .transition(DrawableTransitionOptions.withCrossFade()) //Here a fading animation

            .apply(
                RequestOptions().placeholder(R.drawable.place_holder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )

            .into(holder.imageItemBinding.imageView)
        ViewCompat.setTransitionName(holder.itemView, "item_image")
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val properties = imageList!!.get(v!!.getTag() as Int)
                if (properties != null) {

                }
            }
        })


    }

    override fun getItemCount(): Int {
        return imageList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}