package com.example.greedy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baselibrary.api.ApiUtils
import com.example.baselibrary.api.RestApiInterface
import com.example.baselibrary.models.TagInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TagInfoViewModel : ViewModel() {
    var mAPIService: RestApiInterface? = null

    //data that we will fetch asynchronously
    private var tagList: MutableLiveData<TagInfo>? = null

    //this method to get the data
    fun getTagInfo( tagName:String): LiveData<TagInfo>? {
        if (tagList == null) {
            tagList = MutableLiveData<TagInfo>()
            //will load it asynchronously from server in this method
            loadTagInfo(tagName)
        }

        return tagList
    }

    private fun loadTagInfo(tagName: String) {
        mAPIService = ApiUtils.getAPIService()
        var stringStringMap = HashMap<String,String>()
        stringStringMap.put("method", "tag.getinfo")
        stringStringMap.put("tag", tagName)
        mAPIService!!.getTagInfo(stringStringMap)!!
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response -> onSuccess(response) }, { t -> onFailure(t) })
    }

    private fun onFailure(t: Throwable) {
        Log.v("sv","")
    }

    private fun onSuccess(response: TagInfo?) {
        tagList!!.setValue(response!!)
        response.tags


    }
}