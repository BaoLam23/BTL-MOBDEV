package com.example.gameinwakingtoearn;
import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Login;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Register;
import android.app.Activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
public class RegisterTest {
    @Rule
    public IntentsTestRule<Register> intentsTestRule = new IntentsTestRule<>(Register.class);

    @Test
    public void checkEmailInputDisplayed() {
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }
    @Test
    public void checkPasswordInputDisplayed() {
        onView(withId(R.id.password)).check(matches(isDisplayed()));

    }

    @Test
    public void checkUsernameInputDisplayed() {
        onView(withId(R.id.username)).check(matches(isDisplayed()));

    }

    @Test
    public void checkRegisterButtonDisplayed() {
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()));
    }

    @Test
    public void checkLoginNowLinkDisplayed() {
        onView(withId(R.id.loginNow)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewClickNavigatesToRegister() {
        onView(withId(R.id.loginNow)).perform(click());
        intended(hasComponent(Login.class.getName()));
    }

}
