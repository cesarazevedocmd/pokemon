package com.example.pokemon.repository.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonAPI {

    companion object {

        var VERSION: String = "v2"
        private const val url = "https://pokeapi.co/"

        private var instance: Retrofit? = null

        private fun getInstance(): Retrofit {
            return instance ?: synchronized(this) {
                instance = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()
                instance!!
            }
        }

        fun <T> create(service: Class<T>): T {
            return getInstance().create(service)
        }

        private fun getHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val build = chain.request().newBuilder()
                        .header("Content-Type", "application/json")
                        .build()
                    chain.proceed(build)
                }.build()
        }
    }
}