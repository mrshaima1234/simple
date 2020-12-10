package com.e.e_commerce.remote

object ApiRepositoryProvider {
    fun providerApiRepository(): ApiRepository {
        return ApiRepository(ApiInterface.create())
    }
}