package com.example.greedy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baselibrary.models.Tags
import com.example.baselibrary.models.TagsList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val imageViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(ImageViewModel::class.java)
//
//        imageViewModel.getTags()!!.observe(
//            this,
//            object : Observer<Tags?> {
//                override fun onChanged(@Nullable imageList: Tags?) {
//
//Log.v("si","")
//                }
//            })
    }
}