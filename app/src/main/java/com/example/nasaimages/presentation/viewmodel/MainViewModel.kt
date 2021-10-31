package com.example.nasaimages.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaimages.data.model.Item
import com.example.nasaimages.domain.SingleUseCaseInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: SingleUseCaseInterface) : ViewModel()  {
    private var _items: MutableLiveData<List<Item>> = MutableLiveData()
    val items: LiveData<List<Item>>
        get() = _items

    private var _isLoading:  MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _errorMessage:  MutableLiveData<Pair<Boolean, String>> = MutableLiveData(Pair(false, ""))
    val errorMessage: LiveData<Pair<Boolean, String>>
        get() = _errorMessage

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val result = useCase.getImages()
            when{
                result.isSuccess -> _items.postValue(result.getOrNull())
                result.isFailure -> _errorMessage.postValue(Pair(true, result.toString()))
            }
            _isLoading.postValue(false)
        }
    }


    fun errorMessageDisplayed() {
        _errorMessage.value = Pair(false, "")
    }
}