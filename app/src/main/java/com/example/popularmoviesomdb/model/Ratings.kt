package com.example.popularmoviesomdb.model

import com.google.gson.annotations.SerializedName

class Ratings {
    @SerializedName("Source")
    var source: String? = null

    @SerializedName("Value")
    var value: String? = null

}