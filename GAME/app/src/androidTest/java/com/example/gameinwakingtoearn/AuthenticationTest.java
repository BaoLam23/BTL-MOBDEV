package com.example.gameinwakingtoearn;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.gameinwakingtoearn.Game.Object.MainUI.Authentication;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Friends;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Login;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Register;
import com.example.gameinwakingtoearn.Game.Object.MainUI.Settings;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameUI;
import com.example.gameinwakingtoearn.Game.Object.Running.RunningStartUI;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AuthenticationTest {

//    @Rule
//    public ActivityTestRule<Authentication> activityRule = new ActivityTestRule<>(Authentication.class, true, true);
    @Rule
    public IntentsTestRule<Login> intentsTestRule = new IntentsTestRule<>(Login.class);

//    public IntentsTestRule<Authentication> intentsTestRule = new IntentsTestRule<>(Authentication.class);

//    @BeforeClass
//    public static void setUpAll() {
//        onView(withId(R.id.email)).perform(typeText("hieu@gmail.com"));
//        onView(withId(R.id.password)).perform(typeText("12345678"));
//        onView(withId(R.id.btn_login)).perform(click());
//    }
//    @Before
//    public void setUp() {
//        activityRule.launchActivity(new Intent());
//    }
//
    @Test
    public void checkUsernameDisplayed() {

        onView(withId(R.id.user_details)).check(matches(isDisplayed()));
    }

    @Test
    public void checkLevelDisplayed() {
        onView(withId(R.id.level)).check(matches(isDisplayed()));

    }
    @Test
    public void checkXpDisplayed() {
        onView(withId(R.id.xpProgressBar)).check(matches(isDisplayed()));

    }
    @Test
    public void checkGameBtnDisplayed() {
        onView(withId(R.id.playGameButton)).check(matches(isDisplayed()));

    }
    @Test
    public void checkFriendBtnDisplayed() {
        onView(withId(R.id.findFriendButton)).check(matches(isDisplayed()));

    }
    @Test
    public void checkWalkingBtnDisplayed() {
        onView(withId(R.id.showMapBut)).check(matches(isDisplayed()));

    }
    @Test
    public void checkSettingBtnDisplayed() {
        onView(withId(R.id.settingButtonAuthentication)).check(matches(isDisplayed()));

    }

    @Test
    public void testBtnClickNavigatesToSetting() {
        onView(withId(R.id.settingButtonAuthentication)).perform(click());
        intended(hasComponent(Settings.class.getName()));

    }
    @Test
    public void testBtnClickNavigatesToFriends() {
        onView(withId(R.id.findFriendButton)).perform(click());
        intended(hasComponent(Friends.class.getName()));

    }
    @Test
    public void testBtnClickNavigatesToWalking() {
        onView(withId(R.id.showMapBut)).perform(click());
        intended(hasComponent(RunningStartUI.class.getName()));

    }
    @Test
    public void testBtnClickNavigatesToGame() {
        onView(withId(R.id.playGameButton)).perform(click());
        intended(hasComponent(GameUI.class.getName()));

    }
}
