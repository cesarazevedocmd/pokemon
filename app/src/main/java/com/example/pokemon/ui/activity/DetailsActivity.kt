package com.example.pokemon.ui.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.repository.model.Pokemon
import com.example.pokemon.ui.customview.CustomViewInformation
import com.example.pokemon.util.ModelConstants
import com.example.pokemon.util.extension.formatToString

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        try {
            val pokemon = getPokemon()
            loadInformation(pokemon)
        } catch (exception: RuntimeException) {
            findViewById<AppCompatTextView>(R.id.activity_details_txv_alert).text = exception.message
        }

    }

    private fun loadInformation(pokemon: Pokemon) {
        with(pokemon) {
            loadImage(pokemon)
            informationView(R.id.activity_details_info_01, "Name", this.name)
            informationView(R.id.activity_details_info_02, "Height", "${this.height}")
            informationView(R.id.activity_details_info_03, "Weight", "${this.weight}")
            informationView(R.id.activity_details_info_04, "Abilities", this.abilities.formatToString())
            informationView(R.id.activity_details_info_05, "Stats", this.stats.formatToString())
            informationView(R.id.activity_details_info_06, "Types", this.types.formatToString())
        }
    }

    private fun loadImage(pokemon: Pokemon) {
        Glide
            .with(this)
            .load(pokemon.frontPhotoUrl)
            .into(findViewById<AppCompatImageView>(R.id.activity_details_front))
        Glide
            .with(this)
            .load(pokemon.backPhotoUrl)
            .into(findViewById<AppCompatImageView>(R.id.activity_details_back))
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