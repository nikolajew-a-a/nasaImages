package com.example.nasaimages.data.network

import com.example.nasaimages.data.model.Collection
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.data.model.Root
import com.example.nasaimages.util.toResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.HashMap


class Network(private val jsonPlaceHolderApi: JsonPlaceHolderApi) : NetworkInterface {
    private val parameters: MutableMap<String, String> = HashMap()

    override suspend fun getImages(): Result<List<Item>> {
        parameters[SEARCH_KEY] = SEARCH_VALUE
        return jsonPlaceHolderApi
            .getImages(parameters)
            .toResult()
            .map { it?.collection }
            .map { it?.items ?: emptyList() }
    }

    companion object {
        private const val SEARCH_KEY = "q"
        private const val SEARCH_VALUE = "best"
    }
}