package com.example.nasaimages.data.network

import com.example.nasaimages.data.model.Item
import io.reactivex.Single
import retrofit2.Response

interface NetworkInterface {
    suspend fun getImages(): Result<List<Item>>
}