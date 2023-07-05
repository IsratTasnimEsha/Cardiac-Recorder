package com.example.gitproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class SplashScreenTest {

    @Rule
    public ActivityScenarioRule<StartActivity> activityRule =
            new ActivityScenarioRule<StartActivity>(StartActivity.class);
    @Test
    public void testSplashScreenText()
    {
        onView(withId(R.id.okay)).check(matches(isDisplayed()));

    }

}
