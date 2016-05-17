package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.jokedisplay.DisplayJokeActivity;


public class MainActivityFragment extends Fragment {

  public MainActivityFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_main, container, false);
    return root;
  }


  public void displayJoke(String joke) {
    Intent intent = new Intent(this.getActivity(), DisplayJokeActivity.class);
    intent.putExtra(DisplayJokeActivity.JOKE, joke);
    startActivity(intent);
  }
}
