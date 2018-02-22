package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.api.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class EndpointsTest {

    @Test
    public void testGetJokes(){
        Context context = getInstrumentation().getContext();
        EndpointsAsyncTask.getJokes(context, new EndpointsAsyncTask.OnTaskFinishListener<List<Joke>>() {
            @Override
            public void onTaskFinish(List<Joke> result) {
                Assert.assertNotNull(result);
                Assert.assertNotEquals(0, result.size());
            }
        });
    }

    public void testGetRandomJoke(){
        Context context = getInstrumentation().getContext();
        EndpointsAsyncTask.getRandomJoke(context, new EndpointsAsyncTask.OnTaskFinishListener<Joke>() {
            @Override
            public void onTaskFinish(Joke result) {
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.getContent());
            }
        });
    }
}
