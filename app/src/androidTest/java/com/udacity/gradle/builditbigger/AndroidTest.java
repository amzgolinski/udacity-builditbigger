package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.udacity.gradle.builditbigger.jokedisplay.DisplayJokeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AndroidTest {

  private static final String DISPLAY_PACKAGE
    = "com.udacity.gradle.builditbigger.jokedisplay";

  @Rule
  public IntentsTestRule<MainActivity> mActivityRule =
    new IntentsTestRule<>(MainActivity.class);

  @Test
  public void testJokeButtonLaunchesDisplay() {

    onView(withId(R.id.joke_button)).perform(click());

    intended(toPackage(DISPLAY_PACKAGE));
    intended(allOf(
      hasExtraWithKey(DisplayJokeActivity.JOKE),
      toPackage(DISPLAY_PACKAGE)));
  }

  @Test
  public void testJokeDisplayed() {

    // Build a result to return when a particular activity is launched.
    Intent jokeIntent = new Intent();
    String joke = "This is a joke";
    jokeIntent.putExtra(DisplayJokeActivity.JOKE, joke);
    ActivityResult result = new ActivityResult(Activity.RESULT_OK, jokeIntent);

    intending(toPackage(DISPLAY_PACKAGE)).respondWith(result);

    onView(withId(R.id.joke_button)).perform(click());

    onView(withId(R.id.joke_text_view)).check(matches(withText(joke)));
  }

}
