package com.example.nasaimages.data.network

import com.example.nasaimages.data.model.Item
import io.reactivex.Single

interface NetworkInterface {
    fun getImages(): Single<List<Item>>
}