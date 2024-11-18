package io.github.vkindl.nbaplayers.core

sealed class Result<out R> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String?) : Result<Nothing>()
}
