package com.app.androidretrofit.Model

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    var page:Int,
    @SerializedName("per_page")
    var perpage:Int,
    var total:Int,
    @SerializedName("total_pages")
    var totalpages:Int,
    var support:Support,
    @SerializedName("data")
    var userlist:MutableList<User>

)
