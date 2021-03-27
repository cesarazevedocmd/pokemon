package com.example.pokemon.ui.viewmodel.provider

import com.example.pokemon.util.empty

enum class Status {
    ERROR,
    UPDATE_IN_PROGRESS,
    LOADING,
    FINISHED
}

class Resource<T>(val status: Status, val data: T?, val message: String) {
    companion object {
        fun <T> loading() = Resource<T>(status = Status.LOADING, data = null, message = empty)
        fun <T> updateInProgress(data: T) = Resource(status = Status.UPDATE_IN_PROGRESS, data = data, message = empty)
        fun <T> error(message: String) = Resource<T>(status = Status.ERROR, data = null, message = message)
        fun <T> finished(data: T) = Resource(status = Status.FINISHED, data = data, message = empty)
    }
}