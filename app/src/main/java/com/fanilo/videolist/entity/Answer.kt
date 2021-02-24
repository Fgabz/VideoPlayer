package com.fanilo.videolist.entity

sealed class Answer<out T : Any> {

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    data class Success<T : Any>(val value: T) : Answer<T>()
    data class Failure(val error: Throwable, val message: String, val type: FailureReason?) : Answer<Nothing>()
}

inline fun <T : Any, R> Answer<T>.onSuccess(onSuccess: (value: T) -> R): Answer<T> {
    if (this is Answer.Success) {
        onSuccess(this.value)
    }
    return this
}

enum class FailureReason {
    NETWORK
}