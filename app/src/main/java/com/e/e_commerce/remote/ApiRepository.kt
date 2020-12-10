package com.e.e_commerce.remote

import DataResponse
import Json4Kotlin_Base
import com.e.e_commerce.prefernce.AppPreferences.api_token
import com.e.e_commerce.ui.model.BrandResponse
import com.e.e_commerce.ui.model.LoginResponse
import io.reactivex.Observable

class ApiRepository(private val apiInterface: ApiInterface) {
    fun login(email: String,password: String): Observable<LoginResponse> = apiInterface.login(email,password)

    fun getBrands(): Observable<BrandResponse> = apiInterface.getBrands()

    fun getProductList(page : Int,sort_by : Int): Observable<Json4Kotlin_Base> = apiInterface.getProductList(page,sort_by)



}
