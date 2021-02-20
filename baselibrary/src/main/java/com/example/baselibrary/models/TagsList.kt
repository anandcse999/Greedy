package com.example.baselibrary.models

import com.google.gson.annotations.SerializedName

class TagsList {
    @SerializedName("name")
    var name = ""

    @SerializedName("count")
    var count = 0

    @SerializedName("reach")
    var reach = 0

    @SerializedName("wiki")
    var wiki: Wiki? = null

    @SerializedName("url")
    var url: String? = null


}