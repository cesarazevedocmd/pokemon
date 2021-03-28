package com.example.pokemon.util

import com.example.pokemon.util.ModelConstants.POKEMON
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class ModelConstantsTest {

    @Test
    fun should_ReturnLabelPOKEMON() {
        assertThat(POKEMON, equalTo("POKEMON"))
    }
}