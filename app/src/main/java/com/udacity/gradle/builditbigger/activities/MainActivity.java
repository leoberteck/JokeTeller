package com.udacity.gradle.builditbigger.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jokeviews.JokeActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.fragments.MainActivityFragment;
import com.udacity.gradle.builditbigger.mvp.MainActivityMvp;
import com.udacity.gradle.builditbigger.mvp.MainActivityPresenter;
import com.udacity.gradle.builditbigger.util.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity implements MainActivityMvp.MainActivityInterface {

    @Nullable
    private SimpleIdlingResource idlingResource;

    private static final MainActivityMvp.MainActivityPresenterInterface presenter = new MainActivityPresenter();

    private ProgressBar progressBar;

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
        presenter.setMainActivity(this);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(presenter.getProgressVisibility());

        Fragment fragment = MainActivityFragment.newInstance(presenter);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
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

    @Override
    public void showJokeActivity(String content) {
        Intent jokeIntent = new Intent(this, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_CONTENT_EXTRA, content);
        startActivity(jokeIntent);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.fail_get_content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoader() {
        if(idlingResource != null) idlingResource.setIdleState(false);
        presenter.setProgressVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        if(idlingResource != null) idlingResource.setIdleState(true);
        presenter.setProgressVisibility(View.GONE);
    }

    @Override
    public void setProgressVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
