package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import com.udacity.gradle.builditbigger.jokeapi.jokeApi.JokeApi;

public class JokeAsyncTask
  extends AsyncTask<Void, Void, String> {

  public static final String LOG_TAG = JokeAsyncTask.class.getSimpleName();

  private static JokeApi jokeApiService = null;
  private JokeCallback mCallback;

  public JokeAsyncTask(JokeCallback jokeCallback) {
    mCallback = jokeCallback;
  }

  @Override
  protected String doInBackground(Void... params) {

    if(jokeApiService == null) {  // Only do this once
      JokeApi.Builder builder =
        new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
        // options for running against local devappserver
        // - 10.0.2.2 is localhost's IP address in Android emulator
        // - turn off compression when running against local devappserver
        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
          @Override
          public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
            throws IOException {
            abstractGoogleClientRequest.setDisableGZipContent(true);
          }
        });
      // end options for devappserver
      jokeApiService = builder.build();
    }

    try {
      String joke = jokeApiService.getJoke().execute().getData();
      Log.d(LOG_TAG, "Retrieved joke" + joke);
      return joke;
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPostExecute(String joke) {
    mCallback.jokeRetrieved(joke);
  }

}
