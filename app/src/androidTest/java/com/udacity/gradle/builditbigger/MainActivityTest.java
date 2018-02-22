package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jokeviews.JokeActivity;
import com.udacity.gradle.builditbigger.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(activityTestRule.getActivity().getIdlingResource());
    }

    @After
    public void unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(activityTestRule.getActivity().getIdlingResource());
    }

    @Test
    public void TestJokeButtonClick(){
        onView(withId(R.id.btn_tell_joke)).perform(click());

        intended(allOf(
            hasComponent(JokeActivity.class.getName())
            , hasExtraWithKey(JokeActivity.JOKE_CONTENT_EXTRA)
        ));
    }
}
