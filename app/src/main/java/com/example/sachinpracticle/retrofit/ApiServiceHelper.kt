package com.example.sachinpracticle.retrofit

import android.text.TextUtils
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


object ApiServiceHelper {
    private var client: ApiService? = null

    init {
        if (client == null) {
            val okHttpClient = OkHttpClient.Builder()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(interceptor)

            okHttpClient.connectTimeout(5, TimeUnit.MINUTES)
            okHttpClient.readTimeout(5, TimeUnit.MINUTES)
            okHttpClient.writeTimeout(5, TimeUnit.MINUTES)
            okHttpClient.interceptors().add(Interceptor { chain ->

                val request: Request.Builder = chain.request()
                    .newBuilder()

                chain.proceed(request.build())
            })
            val retofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            client = retofitClient.create(ApiService::class.java);
        }
    }

    suspend fun getData() = client?.getData()
}