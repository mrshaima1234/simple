package com.e.e_commerce.ui.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("message")
    val message: String,

    @SerializedName("code")
    val code: String
)