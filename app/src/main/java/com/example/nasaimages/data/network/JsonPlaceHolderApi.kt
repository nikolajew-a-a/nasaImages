package com.example.nasaimages.data.network

import android.net.NetworkRequest
import com.example.nasaimages.data.model.Root
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface JsonPlaceHolderApi {
    @GET("/search")
    suspend fun getImages(@QueryMap parameters: Map<String, String>): Response<Root>
}