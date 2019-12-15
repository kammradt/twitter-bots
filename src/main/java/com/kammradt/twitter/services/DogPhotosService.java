package com.kammradt.twitter.services;

import com.kammradt.twitter.enums.BotNameEnum;
import com.kammradt.twitter.services.authentication.AuthenticationService;
import com.vdurmont.emoji.EmojiManager;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import static com.kammradt.twitter.utils.FileUtils.getInputStreamFromUrl;
import static com.kammradt.twitter.utils.RandomUtils.getRandomNumber;

@Service
public class DogPhotosService {

    @Autowired private AuthenticationService authenticationService;

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
            stars.append(EmojiManager.getForAlias("sparkles").getUnicode()).append(" ");

        StringBuilder dogs = new StringBuilder();
        for (int i = 0; i < range + 2; i++)
            dogs.append(EmojiManager.getForAlias("dog").getUnicode()).append(" ");

        return stars.toString() + dogs.toString() + stars.toString();
    }

}
