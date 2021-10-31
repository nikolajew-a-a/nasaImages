package com.example.nasaimages.di.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaimages.presentation.MainActivity
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
    private val models: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = models[modelClass]
            ?: throw IllegalArgumentException("$modelClass not found")

        @Suppress("UNCHECKED_CAST")
        return viewModelProvider.get() as T
    }
}

inline fun <T, reified VM : ViewModel> T.injectViewModel(
    viewModelClass: KClass<VM> = VM::class
) where T : AppCompatActivity, T : LifecycleOwner = lazy(this) {
    ViewModelProvider(
        this,
        (this as MainActivity).mainActivityComponent.provideFactory()
    )[viewModelClass.java]
}

inline fun <T, reified VM : ViewModel> T.injectViewModel(
    viewModelClass: KClass<VM> = VM::class
) where T : Fragment, T : LifecycleOwner = lazy(this) {
    ViewModelProvider(
        this,
        (activity as MainActivity).mainActivityComponent.provideFactory()
    )[viewModelClass.java]
}

inline fun <T, reified VM : ViewModel> T.injectActivityViewModel(
    viewModelClass: KClass<VM> = VM::class
) where T : Fragment, T : LifecycleOwner = lazy(this) {
    val owner = activity ?: throw IllegalArgumentException("Activity must be attached")
    ViewModelProvider(
        owner,
        (activity as MainActivity).mainActivityComponent.provideFactory()
    )[viewModelClass.java]
}