package com.example.nasaimages.data.network

import com.example.nasaimages.data.model.Collection
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.data.model.Root
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.HashMap


class Network(private val jsonPlaceHolderApi: JsonPlaceHolderApi) : NetworkInterface {
    private val parameters: MutableMap<String, String> = HashMap()

    override fun getImages(): Single<List<Item>> {
        parameters[SEARCH_KEY] = SEARCH_VALUE
        return jsonPlaceHolderApi
            .getImages(parameters)
            .map(Root::collection)
            .map(Collection::items)
            .subscribeOn(Schedulers.io())
    }

    companion object {
        private const val SEARCH_KEY = "q"
        private const val SEARCH_VALUE = "best"
    }
}