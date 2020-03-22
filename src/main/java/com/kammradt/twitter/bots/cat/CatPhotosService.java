package com.kammradt.twitter.bots.cat;

import com.kammradt.twitter.commom.authentication.AuthenticationService;
import com.kammradt.twitter.commom.authentication.BotNameEnum;
import com.vdurmont.emoji.EmojiManager;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import java.util.List;

import static com.kammradt.twitter.commom.utils.FileUtils.getInputStreamFromUrl;
import static com.kammradt.twitter.commom.utils.RandomUtils.getRandomEmoji;
import static com.kammradt.twitter.commom.utils.RandomUtils.getRandomNumber;

@AllArgsConstructor
@Service
public class CatPhotosService {

    private final AuthenticationService authenticationService;
    private final Environment env;

    @SneakyThrows
    public void tweetCatPhoto() {
        Twitter twitter = authenticationService.authenticate(BotNameEnum.BOTFTSDEGATINHO);

        StatusUpdate tweetThatWillBeDone = new StatusUpdate(getTextWithStarsAndCats());
        tweetThatWillBeDone.setMedia("", getInputStreamFromUrl(getCatImageUrl()));

        twitter.updateStatus(tweetThatWillBeDone);
    }

    @SneakyThrows
    private String getCatImageUrl() {
        String url = "https://api.thecatapi.com/v1/images/search";

        HttpResponse<JsonNode> response = Unirest.get(url)
                .header("x-api-key", env.getProperty("apiKey." + BotNameEnum.BOTFTSDEGATINHO))
                .asJson();

        if (response.getStatus() != 200)
            throw new Exception("Error getting image of a cat");

        JSONObject jsonResponse = (JSONObject) response.getBody().getArray().get(0);

        return jsonResponse.getString("url");
    }


    private String getTextWithStarsAndCats() {
        int range = getRandomNumber(4);

        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < range; i++)
            stars.append(getStarEmoji()).append(" ");

        StringBuilder cats = new StringBuilder();
        for (int i = 0; i < range + 2; i++)
            cats.append(getRandomCatEmoji()).append(" ");

        return stars.toString() + cats.toString() + stars.toString();
    }

    private String getRandomCatEmoji() {
        List<String> catsOptions = List.of("smiley_cat", "smile_cat", "heart_eyes_cat", "kissing_cat", "smirk_cat", "scream_cat", "crying_cat_face", "joy_cat", "pouting_cat");
        return getRandomEmoji(catsOptions);
    }

    private String getStarEmoji() {
        return EmojiManager.getForAlias("dizzy").getUnicode();
    }


}
