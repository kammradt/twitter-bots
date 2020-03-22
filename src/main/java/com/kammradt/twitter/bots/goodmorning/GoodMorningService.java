package com.kammradt.twitter.bots.goodmorning;


import com.kammradt.twitter.commom.authentication.AuthenticationService;
import com.kammradt.twitter.commom.authentication.BotNameEnum;
import com.vdurmont.emoji.EmojiManager;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;

import java.util.List;
import java.util.stream.Collectors;

import static com.kammradt.twitter.commom.utils.RandomUtils.getRandomEmoji;


@AllArgsConstructor
@Service
public class GoodMorningService {

    private final AuthenticationService authenticationService;

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