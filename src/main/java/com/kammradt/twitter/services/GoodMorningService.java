package com.kammradt.twitter.services;


import com.kammradt.twitter.enums.BotNameEnum;
import com.kammradt.twitter.services.authentication.AuthenticationService;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import twitter4j.Twitter;

import java.util.List;
import java.util.stream.Collectors;

import static com.kammradt.twitter.utils.RandomUtils.getRandomEmoji;


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
        List<String> allEmojis = EmojiManager.getAll()
                .stream()
                .map(emoji -> emoji.getAliases().get(0))
                .collect(Collectors.toList());

        StringBuilder emojisText = new StringBuilder();
        for (int i = 0; i < 3; i++)
            emojisText.append(getRandomEmoji(allEmojis)).append(" ");

        return emojisText.toString();
    }
}