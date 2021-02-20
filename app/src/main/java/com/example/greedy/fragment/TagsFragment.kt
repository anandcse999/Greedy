package com.example.greedy.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.baselibrary.models.Tags
import com.example.baselibrary.models.TagsList
import com.example.greedy.adapter.TagsAdapter
import com.example.greedy.databinding.TagsFragmentBinding
import com.example.greedy.viewmodel.TagFragmentViewModel
import com.example.greedy.viewmodel.ViewModelFactory


class TagsFragment:Fragment() {
    lateinit var tagsFragmentBinding: TagsFragmentBinding
    lateinit var mContext:Context
    var tagItems =listOf<TagsList>()
    lateinit var adapter: TagsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tagsFragmentBinding= TagsFragmentBinding.inflate(inflater, container, false).apply {

            var param = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
            param.setMargins(40, 0, 0, 0)
           rvTags.layoutParams = param
            rvTags.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

            ibMore.setOnClickListener {
                if (adapter.overviewList.size>10) {
                    adapter.update(tagItems.take(10))
                    ibMore.animate().rotationBy(180f).setDuration(500)
                        .setInterpolator(LinearInterpolator()).start()

                }else{
                    adapter.update(tagItems)
                    ibMore.animate().rotationBy(180f).setDuration(500)
                        .setInterpolator(LinearInterpolator()).start()

                }
            }

        }
        val imageViewModel = ViewModelProvider(this, ViewModelFactory()).get(TagFragmentViewModel::class.java)

        imageViewModel.getTags()!!.observe(
            viewLifecycleOwner,
            object : Observer<Tags?> {
                override fun onChanged(@Nullable tagList: Tags?) {
                    tagItems = tagList!!.tags!!
                    if (tagItems.size > 10)
                        adapter = TagsAdapter(mContext, tagList!!.tags!!.take(10), 3)
                    else
                        adapter = TagsAdapter(mContext, tagList!!.tags!!, 3)
                    tagsFragmentBinding.rvTags.adapter = adapter

                    Log.v("si", "")
                }
            })

        return tagsFragmentBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }
}