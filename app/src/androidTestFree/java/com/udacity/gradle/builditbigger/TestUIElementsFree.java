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
public class TestUIElementsFree {

  private static final String INTERSTITIAL_AD_CLOSE_BUTTON_DESCRIPTION =
    "Interstitial close button";

  @Rule
  public ActivityTestRule<MainActivity> mActivityRule =
    new ActivityTestRule(MainActivity.class);

  @Test
  public void testContentVisibleOnActivityStart() {
    onView(withId(R.id.instructions_text_view)).check(matches(isDisplayed()));
  }

  @Test
  public void testBannerAdDisplayed() {
    onView(withId(R.id.ad_view)).check(matches(isDisplayed()));
  }

  @Test
  public void testInterstitialAdDisplayed() {

    onView(withId(R.id.joke_button)).perform(click());

    onView(withContentDescription(INTERSTITIAL_AD_CLOSE_BUTTON_DESCRIPTION))
      .check(matches(isDisplayed()));

    onView(withContentDescription(INTERSTITIAL_AD_CLOSE_BUTTON_DESCRIPTION))
      .perform(click());

  }

}
