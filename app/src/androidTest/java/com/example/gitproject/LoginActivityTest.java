package com.example.gitproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test

    public void testLoginActivity() {
        //onView(withId(R.id.)).check(matches(isDisplayed()));
       onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.login)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.textView)).check(matches(isDisplayed()));

        String phoneNumber = "sunzidulislam12@gmail.com";
        String password = "111111111";

        onView(withId(R.id.email)).perform(typeText(phoneNumber));
        onView(withId(R.id.password)).perform(typeText(password));
        Espresso.pressBack();
        onView(withId(R.id.login)).perform(click());
    }

}
