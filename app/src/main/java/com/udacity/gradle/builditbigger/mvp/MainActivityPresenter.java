package com.udacity.gradle.builditbigger.mvp;

import android.content.Context;
import android.view.View;

import com.udacity.gradle.builditbigger.api.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;
import com.udacity.gradle.builditbigger.util.AbstractSimplePromise;
import com.udacity.gradle.builditbigger.util.SimplePromise;

public class MainActivityPresenter implements MainActivityMvp.MainActivityPresenterInterface {

    private MainActivityMvp.MainActivityInterface mainActivity;
    private int progressVisibility = View.GONE;

    @Override
    public void setMainActivity(MainActivityMvp.MainActivityInterface mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public int getProgressVisibility() {
        return progressVisibility;
    }

    @Override
    public void setProgressVisibility(int progressVisibility) {
        this.progressVisibility = progressVisibility;
        if(mainActivity != null){
            mainActivity.setProgressVisibility(this.progressVisibility);
        }
    }

    @Override
    public SimplePromise getTellJokePromise(Context context) {
        return new TellJokePromise(context, mainActivity);
    }

    private static class TellJokePromise extends AbstractSimplePromise {
        private final MainActivityMvp.MainActivityInterface mainActivity;
        private Joke result;

        TellJokePromise(Context context, final MainActivityMvp.MainActivityInterface mainActivity) {
            this.mainActivity = mainActivity;
            //Show loader while joke is loading;
            mainActivity.showLoader();
            //Loads Joke in background;
            EndpointsAsyncTask.getRandomJoke(context, new EndpointsAsyncTask.OnTaskFinishListener<Joke>() {
                @Override
                public void onTaskFinish(Joke result) {
                    TellJokePromise.this.result = result;
                    setResolved(true);
                }
            });
        }

        @Override
        protected void tryRunDelayedAction(){
            //Hide loader when prior to action run
            mainActivity.hideLoader();
            if(result != null){
                //If joke successfully loaded, start JokeActivity
                mainActivity.showJokeActivity(result.getContent());
                if(actionPerformedListener != null){
                    actionPerformedListener.onActionPerformed();
                }
            } else {
                //If joke is null, then show error message
                mainActivity.showErrorMessage();
                if(actionFailListener != null){
                    actionFailListener.onActionFail();
                }
            }
        }
    }
}
