package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

public class AsyncTest extends AndroidTestCase {

  JokeAsyncTask jokeAsyncTask;
  String joke;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    joke = null;

    JokeCallback callback = new JokeCallback() {
      @Override
      public void jokeRetrieved(String joke) {
        // empty
      }
    };

    jokeAsyncTask = new JokeAsyncTask(callback) {

      @Override
      protected void onPostExecute(String joke) {
        // empty
      }
    };

  }

  public void testJokeTaskReturnValue() {

    try {
      jokeAsyncTask.execute();
      joke = jokeAsyncTask.get(10, TimeUnit.SECONDS);
      assertNotNull(joke);
    } catch (Exception e) {
      fail("Task timed out.");
    }
  }

}
