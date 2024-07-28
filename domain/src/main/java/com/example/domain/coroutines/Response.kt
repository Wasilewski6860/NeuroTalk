package com.example.domain.coroutines

sealed class Response<out T> {
    class Success<T>(val value: T) : Response<T>()
    class Failure(val error: Throwable) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[value=$value]"
            is Failure -> "Failure[error=$error]"
        }
    }
}

/**
 * Return [Try.Success.value] or null
 */
fun <T : Any> Response<T>.getOrNull(): T? = (this as? Response.Success)?.value

/**
 * Return [Try.Success.value] or [default]
 */
fun <T : Any> Response<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Return [Try.Success.value] or throw [throwable] if defined or [Try.Failure.error]
 */
fun <T : Any> Response<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is Response.Success -> value
        is Response.Failure -> throw throwable ?: error
    }
}
