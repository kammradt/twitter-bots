package com.kammradt.twitter.utils;

import com.vdurmont.emoji.EmojiManager;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    public static int getRandomNumber(Number range) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(range.intValue());
    }

    public static String getRandomEmoji(List<String> emojiOptions) {
        String randomEmoji = emojiOptions.get(getRandomNumber(emojiOptions.size()));
        return EmojiManager.getForAlias(randomEmoji).getUnicode();
    }

}
