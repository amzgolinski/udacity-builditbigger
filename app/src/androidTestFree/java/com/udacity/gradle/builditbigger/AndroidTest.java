package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.udacity.gradle.builditbigger.jokedisplay.DisplayJokeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class AndroidTest {

  private static final String FREE = "com.udacity.gradle.builditbigger.free";

  @Rule
  public ActivityTestRule<MainActivity> mActivityRule =
    new ActivityTestRule(MainActivity.class);

  @Test
  public void testContentVisibleOnActivityStart() {
    onView(withId(R.id.instructions_text_view)).check(matches(isDisplayed()));
  }

  @Test
  public void testAdDisplayed() {
    onView(withId(R.id.ad_view)).check(matches(isDisplayed()));
  }

  @Test
  public void testInterstitialAd() {

    onView(withId(R.id.joke_button)).perform(click());

    onView(withContentDescription("Interstitial close button")).check(matches(isDisplayed()));

    onView(withContentDescription("Interstitial close button")).perform(click());

  }

}
