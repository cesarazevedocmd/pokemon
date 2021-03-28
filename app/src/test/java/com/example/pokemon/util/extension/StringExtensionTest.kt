package com.example.pokemon.util.extension

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StringExtensionTest {

    @Test
    fun should_FormatListOfString_ToString() {
        val items = listOf("A", "B", "C", "D")
        val expectedResult = "A, B, C, D"

        val result = items.formatToString()

        assertThat(result, equalTo(expectedResult))
    }
}