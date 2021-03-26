package com.example.nasaimages.data.network

import com.example.nasaimages.data.model.Root
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface JsonPlaceHolderApi {
    @GET("/search")
    fun getImages(@QueryMap parameters: Map<String, String>): Single<Root>
}