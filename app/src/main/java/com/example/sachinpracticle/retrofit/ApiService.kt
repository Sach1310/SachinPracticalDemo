package com.example.sachinpracticle.retrofit

import com.example.sachinpracticle.model.DataModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(getData)
    suspend fun getData(): Response<ArrayList<DataModel>>

}