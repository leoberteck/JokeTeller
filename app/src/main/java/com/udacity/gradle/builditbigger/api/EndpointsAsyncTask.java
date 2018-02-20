package com.udacity.gradle.builditbigger.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.BuildConfig;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class EndpointsAsyncTask {

    private static MyApi myApiService = null;

    public static void getJokes(Context context, OnTaskFinishListener<List<Joke>> listener){
        new GetJokesEndpoint(listener).execute(context);
    }

    public static void getRandomJoke(Context context, OnTaskFinishListener<Joke> listener){
        new GetRandomJokeEndpoint(listener).execute(context);
    }

    private abstract static class AbstractEndpintTask<T> extends AsyncTask<Object, Void, T>{

        private OnTaskFinishListener<T> listener;

        AbstractEndpintTask(OnTaskFinishListener<T> listener) {
            this.listener = listener;
        }

        @Override
        protected T doInBackground(Object... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(BuildConfig.ENDPOINTS_SERVER_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
                // end options for devappserver

                myApiService = builder.build();
            }
            Context context = (Context) params[0];
            return doWorK(context, myApiService, params);
        }

        abstract T doWorK(Context context, MyApi myApi, Object... params);

        @Override
        protected void onPostExecute(T t) {
            super.onPostExecute(t);
            if(listener != null) {
                listener.onTaskFinish(t);
            }
        }
    }

    private static class GetJokesEndpoint extends AbstractEndpintTask<List<Joke>>{
        private static final String TAG = GetJokesEndpoint.class.getSimpleName();

        GetJokesEndpoint(OnTaskFinishListener<List<Joke>> listener) {
            super(listener);
        }

        @Override
        List<Joke> doWorK(Context context, MyApi myApi, Object... params) {
            List<Joke> jokeList = new ArrayList<>();
            try {
                jokeList = myApiService.getJokes().execute().getItems();
            } catch (Exception e) {
                Log.e(TAG, "Failed to retrieve Jokes. ", e);
            }
            return jokeList;
        }
    }

    private static class GetRandomJokeEndpoint extends AbstractEndpintTask<Joke>{

        private static final String TAG = GetRandomJokeEndpoint.class.getSimpleName();

        GetRandomJokeEndpoint(OnTaskFinishListener<Joke> listener) {
            super(listener);
        }

        @Override
        Joke doWorK(Context context, MyApi myApi, Object... params) {
            Joke joke = null;
            try {
                joke = myApiService.getRandomJoke().execute();
            } catch (Exception e) {
                Log.e(TAG, "Failed to retrieve Jokes. ", e);
            }
            return joke;
        }
    }

    public interface OnTaskFinishListener<T>{
        void onTaskFinish(T result);
    }
}