package com.udacity.gradle.builditbigger.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokeviews.JokeActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.api.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;
import com.udacity.gradle.builditbigger.util.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity {

    @Nullable
    private SimpleIdlingResource idlingResource;

    @NonNull
    @VisibleForTesting
    public SimpleIdlingResource getIdlingResource() {
        if(idlingResource == null){
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        final Context context = this;
        if(idlingResource != null) idlingResource.setIdleState(false);
        EndpointsAsyncTask.getRandomJoke(context, new EndpointsAsyncTask.OnTaskFinishListener<Joke>() {
            @Override
            public void onTaskFinish(Joke result) {
                if(idlingResource != null) idlingResource.setIdleState(true);
                Intent jokeIntent = new Intent(context, JokeActivity.class);
                jokeIntent.putExtra(
                    JokeActivity.JOKE_CONTENT_EXTRA
                    , result != null ? result.getContent() : getString(R.string.fail_get_content)
                );
                startActivity(jokeIntent);
            }
        });
    }
}
