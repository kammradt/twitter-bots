package com.kammradt.twitter.utils;

import java.util.Random;

public class RandomUtils {

    public static int getRandomNumber(Integer range) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(range);
    }

}
