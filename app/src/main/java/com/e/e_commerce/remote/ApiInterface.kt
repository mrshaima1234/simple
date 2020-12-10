package com.e.e_commerce.remote

import DataResponse
import Json4Kotlin_Base
import com.e.e_commerce.ui.model.BrandResponse
import com.e.e_commerce.ui.model.LoginResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<LoginResponse>


    @GET("get-brand-types")
    fun getBrands(): Observable<BrandResponse>



    @GET("get-products")
    fun getProductList(
        @Query("page") page: Int,
        @Query("sort_by") sort_by: Int
    ): Observable<Json4Kotlin_Base>


    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .baseUrl(ConstantsApi.LIVE_BASE_URL)
                .client(initializeClient())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

        private fun initializeClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            //System.setProperty("http.keepAlive", "false");
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(SupportInterceptor())
                .authenticator(SupportInterceptor())
            return builder.build()
        }
    }
}
