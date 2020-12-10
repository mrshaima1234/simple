package com.e.e_commerce.remote

import android.util.Log
import com.e.e_commerce.prefernce.AppPreferences
import okhttp3.*
import java.io.IOException


class SupportInterceptor: Interceptor, Authenticator {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if(!AppPreferences.api_token.equals("")){
            Log.e("12333",AppPreferences.api_token)
            request = request?.newBuilder()
                ?.addHeader("Authorization", "Bearer "+ AppPreferences.api_token)
                ?.build()
        }else {
            request = request?.newBuilder()
                ?.addHeader("Content-Type", "application/json")
                ?.addHeader("Accept", "application/json")
                ?.build()
        }
        return chain.proceed(request)
    }
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        var requestAvailable: Request? = null
        try {
            if(!AppPreferences.api_token.equals("")){
                Log.e("12333",AppPreferences.api_token)
                requestAvailable = response?.request?.newBuilder()
                    ?.addHeader("Authorization", "Bearer "+ AppPreferences.api_token)
                    ?.build()
            }
            return requestAvailable
        } catch (ex: Exception) {
            Log.e("12333",ex.message)
        }
        return requestAvailable
    }

}