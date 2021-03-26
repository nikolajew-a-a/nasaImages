package com.example.nasaimages.di.components

import android.app.Activity
import com.example.nasaimages.di.modules.MainViewModelModule
import com.example.nasaimages.di.modules.NetworkModule
import com.example.nasaimages.di.modules.RepositoryModule
import com.example.nasaimages.di.modules.SingleUseCaseModule
import com.example.nasaimages.presentation.viewmodel.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainViewModelModule::class, SingleUseCaseModule::class, RepositoryModule::class, NetworkModule::class])
interface MainActivityComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder
        fun build(): MainActivityComponent
    }

    //fun inject(activity: MainViewModel)
    fun getMainViewModel(): MainViewModel
}