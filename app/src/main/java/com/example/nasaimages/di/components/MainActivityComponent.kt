package com.example.nasaimages.di.components

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimages.di.modules.NetworkModule
import com.example.nasaimages.di.modules.RepositoryModule
import com.example.nasaimages.di.modules.SingleUseCaseModule
import com.example.nasaimages.presentation.viewmodel.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import com.example.nasaimages.di.viewmodel.VMModel

@Singleton
@Component(
    modules = [
        SingleUseCaseModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        VMModel::class]
)
interface MainActivityComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder
        fun build(): MainActivityComponent
    }

    fun getMainViewModel(): MainViewModel

    fun provideFactory(): ViewModelProvider.Factory
}