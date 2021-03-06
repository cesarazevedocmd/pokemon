package com.example.pokemon.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.ui.activity.adapter.PokemonAdapter
import com.example.pokemon.ui.viewmodel.PokemonViewModel
import com.example.pokemon.ui.viewmodel.provider.Status
import com.example.pokemon.util.ModelConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonActivity : AppCompatActivity() {

    private lateinit var txvAlert: AppCompatTextView
    private lateinit var txvQuantity: AppCompatTextView
    private lateinit var loading: ProgressBar

    private var canLoadMoreItems = true

    private val viewModel: PokemonViewModel by viewModel()

    private val pokemonAdapter: PokemonAdapter by lazy {
        PokemonAdapter {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(ModelConstants.POKEMON, it)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        initViews()
        loadViews()
        setupObservable()
        setupRecyclerView()
        if (savedInstanceState == null) {
            viewModel.listItems()
        }
    }

    private fun loadViews() {
        updateQuantity()
    }

    private fun initViews() {
        txvAlert = findViewById(R.id.activity_pokemon_txv_alert)
        txvQuantity = findViewById(R.id.activity_pokemon_txv_quantity)
        loading = findViewById(R.id.activity_pokemon_loading)
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this@PokemonActivity, LinearLayoutManager.VERTICAL, false)
        with(findViewById<RecyclerView>(R.id.activity_pokemon_rcv)) {
            this.layoutManager = linearLayoutManager
            this.adapter = pokemonAdapter
            this.addOnScrollListener(createScrollListener(linearLayoutManager))
        }
    }

    private fun createScrollListener(layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val limitUntilLastElement = 10
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition() + limitUntilLastElement

                    if (canLoadMoreItems && ((visibleItemCount + pastVisibleItems) >= totalItemCount)) {
                        canLoadMoreItems = false
                        viewModel.listItems()
                        canLoadMoreItems = true
                    }
                }
            }
        }
    }

    private fun setupObservable() {
        viewModel.items().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    loading.visibility = VISIBLE
                    txvAlert.visibility = GONE
                }
                Status.UPDATE_IN_PROGRESS -> {
                    loading.visibility = VISIBLE
                    txvAlert.visibility = GONE
                    it.data?.apply { updateAdapter(this) }
                }
                Status.ERROR -> {
                    loading.visibility = GONE
                    txvAlert.visibility = VISIBLE
                    txvAlert.text = it.message
                    updateQuantity()
                }
                Status.FINISHED -> {
                    loading.visibility = GONE
                    txvAlert.visibility = GONE
                    it.data?.apply { updateAdapter(this) }
                }
            }
        })
    }

    private fun updateQuantity() {
        txvQuantity.text = String.format("Quantity: ${pokemonAdapter.itemCount}")
    }

    private fun updateAdapter(items: List<Pokemon>) {
        pokemonAdapter.setItems(items)
        updateQuantity()
    }
}