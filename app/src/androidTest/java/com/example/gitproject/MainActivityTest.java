package com.example.gitproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Contains Espresso tests for the MainActivity functionality.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<AddActivity> activityRule =
            new ActivityScenarioRule<>(AddActivity.class);

    /**
     * Tests the check app name functionality.
     * Verifies if the app name "Cardio" is displayed on the screen.
     */
    @Test
    public void testCheckAppName() {
        onView(withText("Cardio")).check(matches(isDisplayed()));
    }


    /**
     * Tests the add measurement functionality.
     * Simulates user interactions by entering values for systolic pressure, diastolic pressure,
     * heart rate, and comment, and then clicks the add button.
     */
    @Test
    public void testAddMeasurement() {
        onView(withId(R.id.Add_Enter_Systolic_pressure)).perform(typeText("120"));
        Espresso.pressBack();
        onView(withId(R.id.Add_Enter_Diastolic_pressure)).perform(typeText("80"));
        Espresso.pressBack();
        onView(withId(R.id.Add_Enter_Heart_Rate)).perform(typeText("80"));
        Espresso.pressBack();
        onView(withId(R.id.ADD_Enter_Comment)).perform(typeText("Good"));
        Espresso.pressBack();
        onView(withId(R.id.ADD_ADDButton)).perform(click());
    }

    /**
     * Tests the update measurement functionality.
     * Simulates user interactions by entering new values for systolic pressure, diastolic pressure,
     * heart rate, and comment, and then clicks the update button.
     */
    @Test
    public void testUpdateMeasurement(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.record_recycler_view)).perform(click());
        SystemClock.sleep(1000);

        Espresso.onView(withId(R.id.Update_Enter_Systolic_pressure)).perform(clearText()).perform(ViewActions.typeText("140"));
        Espresso.onView(withId(R.id.Update_Enter_Diastolic_pressure)).perform(clearText()).perform(ViewActions.typeText("100"));
        Espresso.onView(withId(R.id.Update_Enter_Heart_Rate)).perform(clearText()).perform(ViewActions.typeText("80"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.Update_Enter_Comment)).perform(clearText()).perform(ViewActions.typeText("Sick"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2000);
        onView(withId(R.id.Update_UpdateButton)).perform(click());
        SystemClock.sleep(5000);
    }
}