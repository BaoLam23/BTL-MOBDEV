package com.example.gameinwakingtoearn;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.gameinwakingtoearn.Game.Object.MainUI.Authentication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class AuthenticationTest {

    public ActivityScenarioRule<Authentication> activityScenarioRule =
            new ActivityScenarioRule<>(Authentication.class);

    @Before
    public void setup() {
        // Mock the Firebase Auth or other necessary components
        FirebaseAuth mockedFirebaseAuth = Mockito.mock(FirebaseAuth.class);
        FirebaseUser mockedFirebaseUser = Mockito.mock(FirebaseUser.class);

        Mockito.when(mockedFirebaseAuth.getCurrentUser()).thenReturn(mockedFirebaseUser);
        // Additional setup for mocking user details if required

        // Inject this mocked instance into your Authentication activity, or replace the FirebaseAuth instance before the test runs
    }
    @Test
    public void checkUIElementsDisplayed() {
        onView(withId(R.id.user_details)).check(matches(isDisplayed()));
        onView(withId(R.id.settingButtonAuthentication)).check(matches(isDisplayed()));
        onView(withId(R.id.findFriendButton)).check(matches(isDisplayed()));
        // Add checks for other important UI elements
    }
}

