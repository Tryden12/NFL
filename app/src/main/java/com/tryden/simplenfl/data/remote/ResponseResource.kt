package com.tryden.simplenfl.data.remote

import retrofit2.Response
import java.lang.Exception

data class ResponseResource<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
    ) {

    companion object {
        fun <T> success(data: Response<T>): ResponseResource<T> {
            return ResponseResource(
                status = Status.Success,
                data = data,
                exception = null
            )
        }
        fun <T> failure(exception: Exception): ResponseResource<T> {
            return ResponseResource(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    // Mimics an enum
    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!
}