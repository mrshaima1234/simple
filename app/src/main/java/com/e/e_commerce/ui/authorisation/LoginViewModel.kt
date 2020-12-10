package com.e.e_commerce.ui.authorisation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.e.e_commerce.base.BaseViewModel
import com.e.e_commerce.interfaces.CommonInterface
import com.e.e_commerce.remote.ApiRepositoryProvider
import com.e.e_commerce.ui.model.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel(private val commonInterface: CommonInterface) : BaseViewModel() {
    @SuppressLint("CheckResult")
    fun login(email: String,password: String): MutableLiveData<LoginResponse> {
        commonInterface.onShowProgress()
        val response: MutableLiveData<LoginResponse> = MutableLiveData<LoginResponse>()
        val repository = ApiRepositoryProvider.providerApiRepository()
        repository.login(email,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                commonInterface.onDismissProgress()
                response.value = result
            }, {
                Log.e("123", "login: $it")
                commonInterface.onDismissProgress()
                commonInterface.onFailure()

            })
        return response
    }
}
