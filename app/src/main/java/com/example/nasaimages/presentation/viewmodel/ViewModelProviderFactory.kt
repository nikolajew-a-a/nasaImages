package com.example.nasaimages.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimages.domain.SingleUseCaseInterface

class ViewModelProviderFactory(var useCase: SingleUseCaseInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainViewModel::class.java -> MainViewModel(useCase) as T
            else -> throw Exception("Class is not MainViewModel")
        }
    }
}