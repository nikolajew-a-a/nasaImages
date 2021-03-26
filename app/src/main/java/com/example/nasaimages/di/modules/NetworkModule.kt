package com.example.nasaimages.di.modules

import com.example.nasaimages.data.network.JsonPlaceHolderApi
import com.example.nasaimages.data.network.Network
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideNetwork(jsonPlaceHolderApi: JsonPlaceHolderApi?): Network {
        return Network(jsonPlaceHolderApi!!)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): JsonPlaceHolderApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(JsonPlaceHolderApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov"
    }
}