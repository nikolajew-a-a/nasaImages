package com.example.nasaimages.data.repository

import com.example.nasaimages.data.model.Item
import io.reactivex.Single

interface RepositoryInterface {
    fun getImages(): Single<List<Item>>
}