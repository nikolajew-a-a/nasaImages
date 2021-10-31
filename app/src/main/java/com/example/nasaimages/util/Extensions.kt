package com.example.nasaimages.util

import retrofit2.Response

fun <T> Response<T>.toResult() = when {
    this.isSuccessful -> Result.success(this.body())
    else -> Result.failure(Exception(this.errorBody()?.string()))
}