package com.example.nasaimages.data.repository

import com.example.nasaimages.data.model.Item
import com.example.nasaimages.data.network.NetworkInterface
import io.reactivex.Single

class Repository (private val network: NetworkInterface) : RepositoryInterface {
    override suspend fun getImages(): Result<List<Item>> = network.getImages()
}
