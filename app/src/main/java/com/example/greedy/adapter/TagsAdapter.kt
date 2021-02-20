package com.example.greedy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.baselibrary.models.TagsList
import com.example.greedy.R
import com.example.greedy.databinding.TagsViewBinding

class TagsAdapter(val mContext: Context, var overviewList: List<TagsList>, val spanCount:Int) : RecyclerView.Adapter<TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {

        val tagsViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.tags_view, parent, false) as TagsViewBinding
        return TagsViewHolder(tagsViewBinding.root, tagsViewBinding)
    }
    fun update( list: List<TagsList>){
        overviewList = list
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.tagsViewBinding.detail = overviewList.get(position)
        holder.tagsViewBinding.executePendingBindings()
        val width = mContext.resources.displayMetrics.widthPixels
        var param = RecyclerView.LayoutParams((width/spanCount) - 80, RecyclerView.LayoutParams.WRAP_CONTENT);
        param.setMargins(0, 0, 40, 40)
        holder.tagsViewBinding.tvTag.layoutParams=param
        holder.tagsViewBinding.tvTag.tag=overviewList.get(position).name
        holder.tagsViewBinding.tvTag.setOnClickListener { view->

            val value = view.getTag().toString()
            val bundle = bundleOf("tagName" to value)

//            val action = TagsFragmentDirections.actionDetail(value)
            view.findNavController().navigate(R.id.action_detail,bundle)
        }


    }

    override fun getItemCount(): Int {
        return overviewList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}