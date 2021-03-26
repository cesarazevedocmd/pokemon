package com.example.pokemon.ui.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.pokemon.R
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.ui.customview.CustomViewInformation
import com.example.pokemon.util.ModelConstants

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        try {
            val pokemon = getPokemon()
            loadInformation(pokemon)
        } catch (exception: RuntimeException) {
            findViewById<AppCompatTextView>(R.id.activity_details_alert).text = exception.message
        }

    }

    private fun loadInformation(pokemon: Pokemon) {
        with(pokemon) {
            informationView(R.id.activity_details_info_01)
            informationView(R.id.activity_details_info_02)
            informationView(R.id.activity_details_info_03)
            informationView(R.id.activity_details_info_04)
            informationView(R.id.activity_details_info_05)
            informationView(R.id.activity_details_info_06)
        }
    }

    private fun getPokemon(): Pokemon {
        return intent.extras?.getParcelable(ModelConstants.POKEMON)
            ?: throw RuntimeException(getString(R.string.message_pokemon_not_found))
    }

    private fun informationView(@IdRes id: Int, title: String, description: String) {
        findViewById<CustomViewInformation>(id).run {
            setTitle(title)
            setDescription(description)
        }
    }
}