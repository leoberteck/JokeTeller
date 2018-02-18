package com.udacity.gradle.builditbigger.activities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jokeprovider.Joker;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity {

    private static Joker joker;

    private Button tellJokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tellJokeButton = findViewById(R.id.btn_tell_joke);
        initJoker();
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
        Toast.makeText(this, joker.getJokes().get(0).getContent(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("StaticFieldLeak")
    private void initJoker(){
        if(joker == null){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        joker = Joker.newInstance();
                    } catch (Exception e) { e.printStackTrace(); }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    tellJokeButton.setVisibility(View.VISIBLE);
                }
            }.execute();
        } else {
            tellJokeButton.setVisibility(View.VISIBLE);
        }
    }
}
