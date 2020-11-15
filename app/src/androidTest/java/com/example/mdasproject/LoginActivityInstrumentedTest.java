package com.example.mdasproject;

import android.content.Context;
import android.text.InputType;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mdasproject", appContext.getPackageName());
    }

    @Test
    public void userCanEnterLoginData(){
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("username"), closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"), closeSoftKeyboard());
    }

    @Test
    public void userClicksLoginButton(){
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.buttonLogin)).check(matches(isClickable()));
    }

    @Test
    public void performLogin(){
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("username"), closeSoftKeyboard());
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
    }


}