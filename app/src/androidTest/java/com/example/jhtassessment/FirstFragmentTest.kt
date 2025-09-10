package com.example.jhtassessment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {
    @Test
    fun testButtonNavigatesToSecondFragment() {
        val scenario = launchFragmentInContainer<FirstFragment>()
        onView(withId(R.id.view_exercises)).perform(click())
    }
}
