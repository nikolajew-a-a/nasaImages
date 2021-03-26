package com.example.nasaimages.di.modules

import com.example.nasaimages.data.network.Network
import com.example.nasaimages.data.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun provideArticlesRepository(network: Network): Repository {
        return Repository(network)
    }
}