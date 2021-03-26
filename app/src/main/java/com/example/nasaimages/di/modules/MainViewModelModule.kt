package com.example.nasaimages.di.modules

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.nasaimages.domain.SingleUseCase
import com.example.nasaimages.presentation.viewmodel.MainViewModel
import com.example.nasaimages.presentation.viewmodel.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainViewModelModule {
    @Singleton
    @Provides
    fun provideArticlesViewModel(useCase: SingleUseCase, activity: Activity): MainViewModel {
        val owner = activity as ViewModelStoreOwner
        return ViewModelProvider(owner, ViewModelProviderFactory(useCase))
                .get(MainViewModel::class.java)
    }
}