package com.example.pokemon.ui.viewmodel.provider

import com.example.pokemon.util.empty

enum class Status {
    ERROR,
    SUCCESS,
    LOADING
}

class Request<T>(val status: Status, val data: T?, val message: String) {
    companion object {
        fun <T> loading() = Request<T>(status = Status.LOADING, data = null, message = empty)
        fun <T> success(data: T) = Request(status = Status.SUCCESS, data = data, message = empty)
        fun <T> error(message: String) = Request<T>(status = Status.ERROR, data = null, message = message)
    }
}