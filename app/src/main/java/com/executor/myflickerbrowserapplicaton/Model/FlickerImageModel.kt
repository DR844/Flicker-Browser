package com.executor.myflickerbrowserapplicaton.Model

import java.io.Serializable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class FlickerImageModel : Serializable {

    @SerializedName("m")
    @Expose
     var m: String? = null
}