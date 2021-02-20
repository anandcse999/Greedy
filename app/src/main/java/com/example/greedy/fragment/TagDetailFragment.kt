package com.example.greedy.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.greedy.adapter.GalleryAdapter
import com.example.baselibrary.models.TagInfo
import com.example.baselibrary.models.Tags
import com.example.greedy.databinding.TagDetailFragmentBinding
import com.example.greedy.viewmodel.*

class TagDetailFragment : Fragment() {
    lateinit var tagDetailFragmentBinding: TagDetailFragmentBinding
    lateinit var mContext: Context
    var tagName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tagName = arguments!!.getString("tagName", "")
        tagDetailFragmentBinding =
            TagDetailFragmentBinding.inflate(inflater, container, false).apply {
                var param = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                );
                param.setMargins(40, 0, 0, 0)
                rvList.layoutParams = param
                rvList.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

            }
        val imageViewModel =
            ViewModelProvider(this, TagInfoFactory()).get(TagInfoViewModel::class.java)

        imageViewModel.getTagInfo(tagName)!!.observe(
            viewLifecycleOwner,
            object : Observer<TagInfo?> {
                override fun onChanged(@Nullable tagList: TagInfo?) {
//                    tagsFragmentBinding.rvTags.adapter = TagsAdapter(mContext, tagList!!.tags!!, 3)
                    tagDetailFragmentBinding.detail = tagList!!.tags
                    tagDetailFragmentBinding.executePendingBindings()

                    Log.v("si", "")
                }
            })
        getAlbum()
        tagDetailFragmentBinding.tvAlbum.setOnClickListener {
            if (tagDetailFragmentBinding.view1.visibility == View.INVISIBLE)
                getAlbum()
            tagDetailFragmentBinding.view1.visibility = View.VISIBLE
            tagDetailFragmentBinding.view2.visibility = View.INVISIBLE
            tagDetailFragmentBinding.view3.visibility = View.INVISIBLE
        }
        tagDetailFragmentBinding.tvArtist.setOnClickListener {
            if (tagDetailFragmentBinding.view2.visibility == View.INVISIBLE)
                getArtist()
            tagDetailFragmentBinding.view1.visibility = View.INVISIBLE
            tagDetailFragmentBinding.view2.visibility = View.VISIBLE
            tagDetailFragmentBinding.view3.visibility = View.INVISIBLE
        }
        tagDetailFragmentBinding.tvTrack.setOnClickListener {
            if (tagDetailFragmentBinding.view3.visibility == View.INVISIBLE)
                getTrack()
            tagDetailFragmentBinding.view1.visibility = View.INVISIBLE
            tagDetailFragmentBinding.view2.visibility = View.INVISIBLE
            tagDetailFragmentBinding.view3.visibility = View.VISIBLE
        }

        return tagDetailFragmentBinding.root
    }

    private fun getAlbum() {
        val albumViewModel =
            ViewModelProvider(this, AlbumViewFactory()).get(AlbumViewModel::class.java)

        albumViewModel.getTagInfo()!!.observe(
            viewLifecycleOwner,
            object : Observer<Tags?> {
                override fun onChanged(@Nullable tagList: Tags?) {
                    tagDetailFragmentBinding.rvList.adapter =
                        GalleryAdapter(mContext, tagList!!.tags!!)

                    Log.v("si", "")
                }
            })
    }
    private fun getArtist() {
        val viewModel =
            ViewModelProvider(this, ArtistViewFactory()).get(ArtistViewModel::class.java)

        viewModel.getArtistData()!!.observe(
            viewLifecycleOwner,
            object : Observer<Tags?> {
                override fun onChanged(@Nullable tagList: Tags?) {
                    tagDetailFragmentBinding.rvList.adapter =
                        GalleryAdapter(mContext, tagList!!.tags!!)

                    Log.v("si", "")
                }
            })
    }
    private fun getTrack() {
        val viewModel =
            ViewModelProvider(this, TrackViewFactory()).get(TracksViewModel::class.java)

        viewModel.getTracks()!!.observe(
            viewLifecycleOwner,
            object : Observer<Tags?> {
                override fun onChanged(@Nullable tagList: Tags?) {
                    tagDetailFragmentBinding.rvList.adapter =
                        GalleryAdapter(mContext, tagList!!.tags!!)

                    Log.v("si", "")
                }
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}