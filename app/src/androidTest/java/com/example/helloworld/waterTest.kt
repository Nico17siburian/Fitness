package com.example.helloworld

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaterTest{
    @Rule @JvmField
    var activityTestRule = ActivityTestRule(WaterIntake::class.java)

    @Test
    fun clickButton1(){
        onView(withId(R.id.Ba)).perform(ViewActions.click())
        onView(withId(R.id.minum)).check(matches(withText("3.38")))
    }

    @Before
    fun setUp(){
        Intents.init()
    }

    @Test
    fun clickButton2(){
        onView(withId(R.id.Bb)).perform(ViewActions.click())
        onView(withId(R.id.minum)).check(matches(withText("6.76")))
    }

    @After
    fun tearDown(){
        Intents.release()
    }
}