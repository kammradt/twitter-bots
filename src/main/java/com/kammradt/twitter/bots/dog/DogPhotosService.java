package com.kammradt.twitter.bots.dog;

import com.kammradt.twitter.commom.authentication.AuthenticationService;
import com.kammradt.twitter.commom.authentication.BotNameEnum;
import com.vdurmont.emoji.EmojiManager;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import static com.kammradt.twitter.commom.utils.FileUtils.getInputStreamFromUrl;
import static com.kammradt.twitter.commom.utils.RandomUtils.getRandomNumber;

@AllArgsConstructor
@Service
public class DogPhotosService {

    private final AuthenticationService authenticationService;

    @SneakyThrows
    public void tweetDogPhoto() {
        Twitter twitter = authenticationService.authenticate(BotNameEnum.BOTFTSDECACHORRO);

        StatusUpdate tweetThatWillBeDone = new StatusUpdate(getTextWithStarsAndDogs());
        tweetThatWillBeDone.setMedia("", getInputStreamFromUrl(getDogImageUrl()));

        twitter.updateStatus(tweetThatWillBeDone);
    }

    @SneakyThrows
    private String getDogImageUrl() {
        String url = "https://dog.ceo/api/breeds/image/random";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        if (response.getStatus() != 200)
            throw new Exception("Error getting image of a dog");

        return response.getBody().getObject().getString("message");
    }

    private String getTextWithStarsAndDogs() {
        int range = getRandomNumber(4);

        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < range; i++)
            stars.append(getSparklesEmoji()).append(" ");

        StringBuilder dogs = new StringBuilder();
        for (int i = 0; i < range + 2; i++)
            dogs.append(getDogEmoji()).append(" ");

        return stars.toString() + dogs.toString() + stars.toString();
    }

    private String getSparklesEmoji() {
        return EmojiManager.getForAlias("sparkles").getUnicode();
    }

    private String getDogEmoji() {
        return EmojiManager.getForAlias("dog").getUnicode();
    }

}
