package com.udacity.gradle.builditbigger.jokedisplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayJokeFragment extends Fragment {


  public DisplayJokeFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView =
      inflater.inflate(R.layout.fragment_display_joke, container, false);

    Intent intent = getActivity().getIntent();
    String joke = intent.getStringExtra(DisplayJokeActivity.JOKE);
    TextView jokeTextView = (TextView) rootView.findViewById(R.id.jokeTextView);
    if (joke != null && joke.length() > 0) {
      jokeTextView.setText(joke);
    }
    return rootView;
  }

}
