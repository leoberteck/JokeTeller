package com.udacity.gradle.builditbigger.mvp;

import android.content.Context;

import com.udacity.gradle.builditbigger.util.SimplePromise;

public interface MainActivityMvp {

    interface MainActivityInterface{

        void showJokeActivity(String content);

        void hideLoader();

        void showErrorMessage();

        void showLoader();

        void setProgressVisibility(int visibility);
    }

    interface MainActivityPresenterInterface {
        void setMainActivity(MainActivityInterface mainActivity);

        int getProgressVisibility();

        void setProgressVisibility(int progressVisibility);

        SimplePromise getTellJokePromise(Context context);
    }
}
