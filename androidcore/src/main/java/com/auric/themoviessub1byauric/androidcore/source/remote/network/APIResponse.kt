package com.auric.themoviessub1byauric.androidcore.source.remote.network

sealed class APIResponse<out R> {

    data class Success<out T>(val data: T) : APIResponse<T>()

    data class Error(val message: String) : APIResponse<Nothing>()

    object Empty : APIResponse<Nothing>()

}