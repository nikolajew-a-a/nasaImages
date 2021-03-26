package com.example.nasaimages.domain

import com.example.nasaimages.data.model.Item
import io.reactivex.Single

interface SingleUseCaseInterface {
    fun getImages(): Single<List<Item>>
}