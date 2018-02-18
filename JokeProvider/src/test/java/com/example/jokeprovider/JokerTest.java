package com.example.jokeprovider;

import org.junit.Assert;
import org.junit.Test;

public class JokerTest {
    @Test
    public void init() throws Exception {
        Joker joker = Joker.newInstance();
        Assert.assertEquals(15, joker.getJokes().size());
    }
}