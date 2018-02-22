package com.example.jokeviews;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_CONTENT_EXTRA = "JOKE_CONTENT_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(getIntent().hasExtra(JOKE_CONTENT_EXTRA)){
            String jokeContent = getIntent().getStringExtra(JOKE_CONTENT_EXTRA);
            TextView textViewJoke = findViewById(R.id.textView_joke);
            textViewJoke.setText(jokeContent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
