package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.jokedisplay.DisplayJokeActivity;


public class MainActivityFragment extends Fragment {

  private static final String DEVICE = AdRequest.DEVICE_ID_EMULATOR;
  private static final String AD_UNIT_ID
    = "ca-app-pub-3940256099942544/1033173712";

  private String mJoke;
  private InterstitialAd mInterstitial;

  public MainActivityFragment() {
    // empty
  }

  public void displayJoke(String joke) {
    mJoke = joke;
    if (mInterstitial.isLoaded()) {
      mInterstitial.show();
    } else {
      launchDisplayJokeActivity();
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_main, container, false);

    // banner ad
    AdView mAdView = (AdView) root.findViewById(R.id.ad_view);
    AdRequest adRequest = new AdRequest.Builder()
      .addTestDevice(DEVICE)
      .build();
    mAdView.loadAd(adRequest);

    // interstitial ad
    mInterstitial = new InterstitialAd(this.getActivity());
    mInterstitial.setAdUnitId(AD_UNIT_ID);
    mInterstitial.setAdListener(new AdListener() {
      @Override
      public void onAdClosed() {
        requestNewInterstitial();
        launchDisplayJokeActivity();
      }
    });

    requestNewInterstitial();

    return root;
  }

  private void launchDisplayJokeActivity(){
    Intent intent = new Intent(this.getActivity(), DisplayJokeActivity.class);
    intent.putExtra(DisplayJokeActivity.JOKE, mJoke);
    startActivity(intent);
  }

  private void requestNewInterstitial() {
    AdRequest adRequest = new AdRequest.Builder()
      .addTestDevice(DEVICE)
      .build();
    mInterstitial.loadAd(adRequest);
  }

}
