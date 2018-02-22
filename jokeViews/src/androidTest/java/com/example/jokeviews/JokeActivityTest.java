package com.example.jokeviews;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class JokeActivityTest {

    private static final String JOKE = "Why did the chicken cross the road? To get to the other side";

    @Rule
    public IntentsTestRule<JokeActivity> activityTestRule = new IntentsTestRule<>(JokeActivity.class, true, false);

    @Before
    public void launch(){
        Intent launchIntent = new Intent();
        launchIntent.putExtra(JokeActivity.JOKE_CONTENT_EXTRA, JOKE);
        activityTestRule.launchActivity(launchIntent);
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void testActivityInitialization() {
        onView(withText(JOKE)).check(ViewAssertions.matches(isDisplayed()));
    }
}
