package com.e.e_commerce.ui.model

import DataResponse
import com.google.gson.annotations.SerializedName

data class BrandResponse (
    @SerializedName("status")
    val status: Boolean, // true
    @SerializedName("error")
    val error: Error, // null
    @SerializedName("data")
    val `data`: List<DataResponse>

)