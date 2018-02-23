package com.udacity.gradle.builditbigger.backend;

import com.example.jokeprovider.Joke;
import com.example.jokeprovider.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.List;
import java.util.Random;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private final Joker joker = Joker.newInstance();
    private final Random random = new Random();

    @ApiMethod(name = "getJokes")
    public List<Joke> getJokes(){
        return joker.getJokes();
    }

    @ApiMethod(name = "getRandomJoke")
    public Joke getRandomJoke(){
        return joker.getJokes().get(nextInt(0, joker.getJokes().size()-1));
    }

    private int nextInt(int min, int max){
        return random.nextInt(max - min + 1) + min;
    }
}
