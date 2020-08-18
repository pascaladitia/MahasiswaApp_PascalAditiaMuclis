package com.pascal.mahasiswaapp_pascaladitiamuclis.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2/mentoring_kotlin_week4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)
}