package com.example.pokemon.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import com.example.pokemon.R
import com.example.pokemon.ui.viewmodel.PokemonViewModel
import com.example.pokemon.ui.viewmodel.provider.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        setupObservable()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        HandlerCompat.postDelayed(Handler(Looper.myLooper()!!), {
            viewModel.listItems()
        }, null, 1000)
    }

    private fun setupObservable() {
        viewModel.items().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    println("PKM_RESULT: LOADING")
                }
                Status.SUCCESS -> {
                    println("PKM_RESULT: SUCCESS ${it.data}")
                }
                Status.ERROR -> {
                    println("PKM_RESULT: ERROR ${it.message}")
                }
            }
        })
    }
}