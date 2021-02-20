package com.example.greedy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baselibrary.api.ApiUtils

import com.example.baselibrary.api.RestApiInterface
import com.example.baselibrary.models.Tags
import com.example.baselibrary.models.TopTags
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TagFragmentViewModel() : ViewModel() {
    var mAPIService: RestApiInterface? = null

    //data that we will fetch asynchronously
    private var tagList: MutableLiveData<Tags>? = null

    //this method to get the data
    fun getTags(): LiveData<Tags>? {
        if (tagList == null) {
            tagList = MutableLiveData<Tags>()
            //will load it asynchronously from server in this method
            loadTags()
        }

        return tagList
    }

    private fun loadTags() {
        mAPIService = ApiUtils.getAPIService()


        mAPIService!!.getTags("tag.getTopTags")!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onSuccess(response) }, { t -> onFailure(t) })
    }

    private fun onFailure(t: Throwable) {
Log.v("sv","")
    }

    private fun onSuccess(response: TopTags?) {
        tagList!!.setValue(response!!.tags);
        response.tags


    }
}