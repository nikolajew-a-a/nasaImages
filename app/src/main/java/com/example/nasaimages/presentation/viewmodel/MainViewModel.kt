package com.example.nasaimages.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.domain.SingleUseCaseInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

class MainViewModel (private val useCase: SingleUseCaseInterface) : ViewModel()  {
    private lateinit var disposable: Disposable

    private var _items: MutableLiveData<List<Item>> = MutableLiveData()
    val items: LiveData<List<Item>>
        get() = _items

    private var _isLoading:  MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _errorMessage:  MutableLiveData<Pair<Boolean, String>> = MutableLiveData(Pair(false, ""))
    val errorMessage: LiveData<Pair<Boolean, String>>
        get() = _errorMessage

    fun getImages() {
        _isLoading.value = true
        disposable = useCase
            .getImages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Item>>() {
                override fun onSuccess(result: List<Item>) {
                    _isLoading.value = false
                    _items.value = result
                }

                override fun onError(e: Throwable) {
                    _isLoading.value = false
                    _errorMessage.value = Pair(true, e.message.toString())
                }
            })
    }


    fun errorMessageDisplayed() {
        _errorMessage.value = Pair(false, "")
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}