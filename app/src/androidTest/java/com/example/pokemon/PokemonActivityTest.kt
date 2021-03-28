package com.example.pokemon

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.pokemon.ui.activity.PokemonActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule(PokemonActivity::class.java, true, false)

    @Before
    fun setup() {
        activity.launchActivity(Intent())
    }

    @Test
    fun runApplication() {

        delay(7000)

        scrollTo(15)

        delay(1500)

        selectItemAtPosition(12)

        delay(3000)

        pressBack()

        delay(1000)

        scrollTo(40)

        delay(1500)

        selectItemAtPosition(35)

        delay(3000)

        pressBack()

        delay(1500)
    }

    private fun selectItemAtPosition(position: Int) {
        onView(withId(R.id.activity_pokemon_rcv))
            .check(matches(isDisplayed()))
            .perform(actionOnItemAtPosition<ViewHolder>(position, click()))
    }

    private fun scrollTo(position: Int) {
        onView(withId(R.id.activity_pokemon_rcv))
            .check(matches(isDisplayed()))
            .perform(scrollToPosition<ViewHolder>(position))
    }

    private fun delay(time: Long) {
        Thread.sleep(time)
    }
}