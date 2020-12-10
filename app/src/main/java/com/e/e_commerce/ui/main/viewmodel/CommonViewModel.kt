package com.e.e_commerce.ui.main.viewmodel

import DataResponse
import Json4Kotlin_Base
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.e.e_commerce.base.BaseViewModel
import com.e.e_commerce.interfaces.CommonInterface
import com.e.e_commerce.remote.ApiRepositoryProvider
import com.e.e_commerce.ui.model.BrandResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommonViewModel(private val commonInterface: CommonInterface) : BaseViewModel() {
    @SuppressLint("CheckResult")
    fun getBrands(): MutableLiveData<BrandResponse> {
        commonInterface.onShowProgress()
        val response: MutableLiveData<BrandResponse> = MutableLiveData<BrandResponse>()
        val repository = ApiRepositoryProvider.providerApiRepository()
        repository.getBrands()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                commonInterface.onDismissProgress()
                response.value = result
            }, {
                commonInterface.onDismissProgress()
                commonInterface.onFailure()

            })
        return response
    }

    @SuppressLint("CheckResult")
    fun getProductList(page : Int, sort_by : Int): MutableLiveData<Json4Kotlin_Base> {
        commonInterface.onShowProgress()
        val response: MutableLiveData<Json4Kotlin_Base> = MutableLiveData<Json4Kotlin_Base>()
        val repository = ApiRepositoryProvider.providerApiRepository()
        repository.getProductList(page,sort_by)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                commonInterface.onDismissProgress()
                response.value = result
            }, {
                commonInterface.onDismissProgress()
                commonInterface.onFailure()

            })
        return response
    }
}