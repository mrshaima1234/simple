package com.e.e_commerce.ui.model


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("access_token")
    val accessToken: String, // “jshjsj”

    @SerializedName("token_type")
    val tokenType: String // Bearer
)