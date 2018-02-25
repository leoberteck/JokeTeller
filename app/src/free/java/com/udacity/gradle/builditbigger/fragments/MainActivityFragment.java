package com.udacity.gradle.builditbigger.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.mvp.MainActivityMvp;
import com.udacity.gradle.builditbigger.util.SimplePromise;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private static InterstitialAd interstitialAd;
    private MainActivityMvp.MainActivityPresenterInterface presenter;
    private static SimplePromise promise;

    public static Fragment newInstance(MainActivityMvp.MainActivityPresenterInterface presenter) {
        MainActivityFragment fragment = new MainActivityFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    public MainActivityFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeButton = root.findViewById(R.id.btn_tell_joke);
        tellJokeButton.setOnClickListener(this);

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        if (interstitialAd == null) {
            interstitialAd = new InterstitialAd(getActivity());
            interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
            interstitialAd.loadAd(new AdRequest.Builder().build());
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    if(promise != null && promise.canResolve()){
                        promise.resolve();
                    }
                }

                @Override
                public void onAdClosed() {
                    if(promise != null && promise.canResolve()){
                        promise.resolve();
                    }
                    interstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
        }
        return root;
    }

    public void setPresenter(MainActivityMvp.MainActivityPresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View view) {
        promise = presenter.getTellJokePromise(getActivity());
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        } else {
            promise.resolve();
        }
    }
}
