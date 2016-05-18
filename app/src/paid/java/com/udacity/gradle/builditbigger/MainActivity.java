package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.jokedisplay.DisplayJokeActivity;

public class MainActivity extends AppCompatActivity implements JokeCallback {

  private ProgressBar mSpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mSpinner = (ProgressBar) findViewById(R.id .progress_bar);
    mSpinner.setVisibility(View.GONE);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void downloadJoke(View view) {
    JokeAsyncTask jokeTask = new JokeAsyncTask((JokeCallback) this);
    mSpinner.setVisibility(View.VISIBLE);
    jokeTask.execute();
  }

  public void jokeRetrieved(String joke) {
    mSpinner.setVisibility(View.GONE);
    this.displayJoke(joke);
  }

  private void displayJoke(String joke) {
    Intent intent = new Intent(this, DisplayJokeActivity.class);
    intent.putExtra(DisplayJokeActivity.JOKE, joke);
    startActivity(intent);
  }

}
