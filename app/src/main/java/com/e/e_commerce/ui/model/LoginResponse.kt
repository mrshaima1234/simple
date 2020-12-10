package com.e.e_commerce.ui.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: Boolean, // true
     @SerializedName("error")
    val error: Error, // null
    @SerializedName("data")
    val `data`: Login

)