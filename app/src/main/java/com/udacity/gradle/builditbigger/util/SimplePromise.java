package com.udacity.gradle.builditbigger.util;

/**
 * This is kinda like a promise... I cannot hold the current thread when resolve() is called,
 * but the concept is very much like a promise.
 *
 * I created this class to try to simplify the interstitial ads problem, i.e, I should
 * load the joke from the server while the user is seeing the ad but, if the ad is not
 * loaded yet, or if the ad failed to load, or if the ad is shown and the user closes it,
 * I then must show the JokeActivity, but I can only show this activity if the Joke has
 * already been returned from server.
 *
 * This class works with two booleans:
 * resolved -> which tells whether the Joke has been loaded from the server or not.
 * resolveRequested -> which tells whether JokeActivity has been requested or not.
 * The Activity can only be shown if the both boolean values are true.
 *
 * Every time one the booleans is set, I check to see if both are true, then I try
 * to run the delayed action.
 */
public interface SimplePromise {
    void resolve();
    void setActionPerformedListener(OnActionPerformedListener listener);
    void setActionFailListener(OnActionFailListener listener);

    boolean isResolved();
    boolean canResolve();

    interface OnActionPerformedListener {
        void onActionPerformed();
    }

    interface OnActionFailListener {
        void onActionFail();
    }
}
