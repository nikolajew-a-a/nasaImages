package com.example.nasaimages.di.modules

import com.example.nasaimages.data.repository.Repository
import com.example.nasaimages.domain.SingleUseCase
import com.example.nasaimages.domain.SingleUseCaseInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SingleUseCaseModule {
    @Singleton
    @Provides
    fun provideSingleUseCase(repository: Repository): SingleUseCaseInterface {
        return SingleUseCase(repository)
    }
}