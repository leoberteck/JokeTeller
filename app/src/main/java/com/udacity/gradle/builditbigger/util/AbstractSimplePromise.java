package com.udacity.gradle.builditbigger.util;

import android.support.annotation.Nullable;

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
public abstract class AbstractSimplePromise implements SimplePromise {

    private boolean resolved = false;
    private boolean resolveRequested = false;
    private boolean canResolve = true;

    @Nullable
    protected OnActionPerformedListener actionPerformedListener;
    @Nullable
    protected OnActionFailListener actionFailListener;

    @Override
    public boolean isResolved() {
        return resolved;
    }

    @Override
    public boolean canResolve() {
        return canResolve;
    }

    @Override
    public void setActionPerformedListener(OnActionPerformedListener listener) {
        this.actionPerformedListener = listener;
    }

    @Override
    public void setActionFailListener(OnActionFailListener listener) {
        this.actionFailListener = listener;
    }

    @Override
    public void resolve() {
        if(canResolve){
            setResolveRequested(true);
            canResolve = false;
        } else {
            throw new IllegalStateException("Promise resolve has already been requested. Cannot request resolution twice");
        }
    }

    protected void setResolved(boolean resolved) {
        this.resolved = resolved;
        if(this.resolved && resolveRequested){
            tryRunDelayedAction();
        }
    }

    private void setResolveRequested(boolean resolvRequested) {
        this.resolveRequested = resolvRequested;
        if(resolved && this.resolveRequested){
            tryRunDelayedAction();
        }
    }

    protected abstract void tryRunDelayedAction();
}
