package com.app.androidretrofit.Model

import com.google.gson.annotations.SerializedName

data class User(

    var id:Int,
    var email:String,
    @SerializedName("first_name")
    var firstname:String,
    @SerializedName("last_name")
    var lastname:String,
    var avatar:String
)
