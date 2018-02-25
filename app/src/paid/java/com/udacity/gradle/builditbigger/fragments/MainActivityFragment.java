package com.udacity.gradle.builditbigger.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.mvp.MainActivityMvp;
import com.udacity.gradle.builditbigger.util.SimplePromise;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button tellJokeButton = root.findViewById(R.id.btn_tell_joke);
        tellJokeButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        promise = presenter.getTellJokePromise(getActivity());
        promise.resolve();
    }

    public void setPresenter(MainActivityMvp.MainActivityPresenterInterface presenter) {
        this.presenter = presenter;
    }
}
