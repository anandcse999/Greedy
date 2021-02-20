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


class TracksViewModel: ViewModel() {
    var mAPIService: RestApiInterface? = null

    //data that we will fetch asynchronously
    private var tagList: MutableLiveData<Tags>? = null

    //this method to get the track data
    fun getTracks(): LiveData<Tags>? {
        if (tagList == null) {
            tagList = MutableLiveData<Tags>()
            //will load it asynchronously from server in this method
            loadTracks()
        }

        return tagList
    }

    private fun loadTracks() {
        mAPIService = ApiUtils.getAPIService()
        var stringStringMap = HashMap<String, String>()
        stringStringMap.put("method", "track.gettoptags")
        stringStringMap.put("artist", "radiohead")

        stringStringMap.put("track", "paranoid+android")
        mAPIService!!.getTopTracks(stringStringMap)!!
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response -> onSuccess(response) }, { t -> onFailure(t) })
    }

    private fun onFailure(t: Throwable) {
        Log.v("fail", t.message.toString())
    }

    private fun onSuccess(response: TopTags?) {
        tagList!!.setValue(response!!.tags)
        response.tags


    }
}