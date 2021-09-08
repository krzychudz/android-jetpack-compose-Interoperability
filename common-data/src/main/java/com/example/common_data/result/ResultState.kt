package com.example.common_data.result

import java.lang.Exception

sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ResultState<T>()
    data class Error(val exception: Throwable) : ResultState<Nothing>()
    object InProgress : ResultState<Nothing>()
}
