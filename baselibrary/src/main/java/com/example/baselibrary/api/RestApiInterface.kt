package com.example.baselibrary.api

import com.example.baselibrary.models.TagInfo
import com.example.baselibrary.models.TopTags

import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query
import retrofit2.http.QueryMap
import kotlin.collections.HashMap

interface RestApiInterface {

    @GET(".")
    fun getTags(@Query("method") name:String): Observable<TopTags?>?
//    fun getTags(@HeaderMap stringStringMap: HashMap<String, String>): Observable<TopTags?>?
    @GET(".")
    fun getTagInfo(@QueryMap(encoded = true) map: HashMap<String,String>):Observable<TagInfo?>
    @GET(".")
    fun getTopTracks(@QueryMap(encoded = true) map: HashMap<String,String>):Observable<TopTags>
    @GET(".")
    fun getTopAlbums(@QueryMap(encoded = true) map: HashMap<String,String>):Observable<TopTags>
    @GET(".")
    fun getTopArtist(@QueryMap(encoded = true) map: HashMap<String,String>):Observable<TopTags>

}