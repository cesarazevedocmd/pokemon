package com.example.pokemon.util

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConstantsTest {

    @Test
    fun should_ReturnZero() {
        assertThat(zero, equalTo(0))
    }

    @Test
    fun should_ReturnEmpty() {
        assertThat(empty, equalTo(""))
    }
}