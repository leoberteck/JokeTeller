package com.example.jokeprovider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

public class Joker {

    public static Joker newInstance() throws URISyntaxException, IOException {
        Joker instance = new Joker();
        instance.init();
        return instance;
    }

    private List<Joke> jokeSet;

    public List<Joke> getJokes() {
        return jokeSet;
    }

    Joker() {}

    void init() throws URISyntaxException, IOException {
        final Gson gson = new GsonBuilder().create();

        final Type type = new TypeToken<List<Joke>>(){}.getType();
        jokeSet = gson.fromJson(json, type);
    }

    private static final String json =
        "[\n"
        + "  {\n"
        + "    \"id\" : \"1\"\n"
        + "    , \"content\" : \"A: I have the perfect son.\\nB: Does he smoke?\\nA: No, he doesn't.\\nB: Does he drink whiskey?\\nA: No, he doesn't.\\nB: Does he ever come home late?\\nA: No, he doesn't.\\nB: I guess you really do have the perfect son. How old is he?\\nA: He will be six months old next Wednesday.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"2\"\n"
        + "  , \"content\" : \"Girl: You would be a good dancer except for two things.\\nBoy: What are the two things?\\nGirl: Your feet. \"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"3\"\n"
        + "  , \"content\" : \"Can a kangaroo jump higher than a house?\\nOf course, a house doesn’t jump at all.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"4\"\n"
        + "  , \"content\" : \"Doctor: \\\"I'm sorry but you suffer from a terminal illness and have only 10 to live.\\\"\\nPatient: \\\"What do you mean, 10? 10 what? Months? Weeks?!\\\"\\nDoctor: \\\"Nine.\\\"\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"5\"\n"
        + "  , \"content\" : \"A man asks a farmer near a field, \\\"Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.\\\"\\nThe farmer says, \\\"Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.\\\"\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"6\"\n"
        + "  , \"content\" : \"Anton, do you think I’m a bad mother?\\nMy name is Paul.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"7\"\n"
        + "  , \"content\" : \"My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"8\"\n"
        + "  , \"content\" : \"What is the difference between a snowman and a snowwoman?\\nSnowballs.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"9\"\n"
        + "  , \"content\" : \"Doctor: Hello, did you come to see me with an eye problem?\\nPatient: Wow, yes, how can you tell?\\nDoctor: Because you came in through the window instead of the door.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"10\"\n"
        + "  , \"content\" : \"A wife goes to consult a psychiatrist about her husband: \\\"My husband is acting so weird. He drinks his morning coffee and then he goes and eats the mug! He only leaves the handle!\\\"\\nPsychiatrist: \\\"Yes, that is weird. The handle is the best part.\\\"\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"11\"\n"
        + "  , \"content\" : \"Doctor: \\\"Do you do sports?\\\"\\nPatient: \\\"Does sex count?\\\"\\nDoctor: \\\"Yes.\\\"\\nPatient: \\\"Then no.\\\"\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"12\"\n"
        + "  , \"content\" : \"Oh darling, since you’ve started dieting, you’ve become such a passionate kisser…\\nWhat do you mean, passionate? I’m looking for food remains!\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"13\"\n"
        + "  , \"content\" : \"In a boomerang shop: \\\"I'd like to buy a new boomerang please. Also, can you tell me how to throw the old one away?\\\"\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"14\"\n"
        + "  , \"content\" : \"Patient: Oh doctor, I’m just so nervous. This is my first operation.\\nDoctor: Don't worry. Mine too.\"\n"
        + "  }\n"
        + "  , {\n"
        + "    \"id\" : \"15\"\n"
        + "  , \"content\" : \"Mr. Smith: \\\"Doctor, you remember this strengthening solution you prescribed me yesterday?\\\"\\nDoctor: \\\"Yes, what’s the matter?\\\"\\nMr. Smith: \\\"I would like to use it but I can’t open the bottle!\\\"\"\n"
        + "  }\n"
        + "]";
}
