package com.example.pokemon.ui.viewmodel.provider

import com.example.pokemon.util.empty

enum class Status {
    ERROR,
    SUCCESS,
    LOADING,
    FINISH
}

class Resource<T>(val status: Status, val data: T?, val message: String) {
    companion object {
        fun <T> loading() = Resource<T>(status = Status.LOADING, data = null, message = empty)
        fun <T> success(data: T) = Resource(status = Status.SUCCESS, data = data, message = empty)
        fun <T> error(message: String) = Resource<T>(status = Status.ERROR, data = null, message = message)
        fun <T> finished() = Resource<T>(status = Status.FINISH, data = null, message = empty)
    }
}