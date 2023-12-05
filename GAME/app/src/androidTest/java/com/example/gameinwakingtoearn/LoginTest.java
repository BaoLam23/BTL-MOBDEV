package com.example.gameinwakingtoearn;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gameinwakingtoearn.Game.Object.MainUI.Login;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Register;
import android.app.Activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public IntentsTestRule<Login> intentsTestRule = new IntentsTestRule<>(Login.class);

    @Test
    public void checkEmailInputDisplayed() {
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

    @Test
    public void checkPasswordInputDisplayed() {
        onView(withId(R.id.password)).check(matches(isDisplayed()));
    }

    @Test
    public void checkLoginButtonDisplayed() {
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

    @Test
    public void checkGoToRegisterDisplayed() {
        onView(withId(R.id.registerNow)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewClickNavigatesToRegister() {
        onView(withId(R.id.registerNow)).perform(click());
        intended(hasComponent(Register.class.getName()));
    }

    @Test
    public void checkProgressBarDisplayedAfterLogin() {
        onView(withId(R.id.email)).perform(typeText("hieu@gmail.com"));
        onView(withId(R.id.password)).perform(typeText("12345678"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }
}
