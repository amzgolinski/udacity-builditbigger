package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.udacity.gradle.builditbigger.jokedisplay.DisplayJokeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestIntentsFree {

  private static final String FREE = "com.udacity.gradle.builditbigger.free";

  @Rule
  public IntentsTestRule<MainActivity> mActivityRule =
    new IntentsTestRule<>(MainActivity.class);

  @Test
  public void testInterstitialAdClosedJokeDisplayed() {

    onView(withId(R.id.joke_button)).perform(click());

    // sleep for 4 seconds to wait for the interstitial ad to load
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      //
    }

    onView(withContentDescription("Interstitial close button"))
      .perform(click());

    intended(allOf(
      hasExtraWithKey(DisplayJokeActivity.JOKE),
      toPackage(FREE)));
  }

}
