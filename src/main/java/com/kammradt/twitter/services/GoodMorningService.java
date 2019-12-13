package com.kammradt.twitter.services;


import com.kammradt.twitter.enums.BotNameEnum;
import com.kammradt.twitter.services.authentication.AuthenticationService;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import twitter4j.Twitter;

import java.util.Random;


@Service
public class GoodMorningService {

    @Autowired private AuthenticationService authenticationService;
    
    @SneakyThrows
    public void tweetGoodMorningWithEmoji() {
        Twitter twitter = authenticationService.authenticate(BotNameEnum.KAMMZINHO);
        String goodMorningTextWithEmojis = "Bom dia " + getThreeRandomEmojis();
        twitter.updateStatus(goodMorningTextWithEmojis);
    }

    private String getThreeRandomEmojis() {
        Object[] allEmojis = EmojiManager.getAll().toArray();

        StringBuilder emojisText = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            Emoji randomEmoji = (Emoji) allEmojis[getRandomNumber(allEmojis.length)];
            emojisText.append(randomEmoji.getUnicode()).append(" ");
        }

        return emojisText.toString();

    }

    private Integer getRandomNumber(Integer range) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(range);
    }

}