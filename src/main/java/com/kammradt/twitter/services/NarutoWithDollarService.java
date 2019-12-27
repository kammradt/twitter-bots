package com.kammradt.twitter.services;

import com.kammradt.twitter.domain.naruto.DollarDTO;
import com.kammradt.twitter.domain.naruto.NarutoCharacter;
import com.kammradt.twitter.enums.BotNameEnum;
import com.kammradt.twitter.services.authentication.AuthenticationService;
import com.kammradt.twitter.services.databaseAccess.NarutoCharacterService;
import com.vdurmont.emoji.EmojiManager;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import java.util.List;

import static com.kammradt.twitter.utils.FileUtils.getInputStreamFromUrl;
import static com.kammradt.twitter.utils.RandomUtils.getRandomNumber;

@Service
public class NarutoWithDollarService {

    @Autowired AuthenticationService authenticationService;
    @Autowired NarutoCharacterService narutoCharacterService;

    @SneakyThrows
    public void tweetNarutoImageWithDollar() {
        Twitter twitter = authenticationService.authenticate(BotNameEnum.DOLARCOMNARUTO);

        NarutoCharacter randomNarutoCharacter = getRandomNarutoCharacter();
        StatusUpdate tweetThatWillBeDone = new StatusUpdate(getText(randomNarutoCharacter));
        tweetThatWillBeDone.setMedia("", getInputStreamFromUrl(randomNarutoCharacter.getImageUrl()));

        twitter.updateStatus(tweetThatWillBeDone);
    }

    private String getText(NarutoCharacter narutoCharacter) {
        DollarDTO dollarInformation = getDollarInformation();

        return new StringBuilder()
                .append(getRandomEmoji()).append(" ").append("Personagem do dia: ").append(narutoCharacter.getName()).append("\n")
                .append(getDollarEmoji()).append(" ").append("Valor de compra: ").append(dollarInformation.getBuy()).append("\n")
                .append(getDollarFlyingEmoji()).append(" ").append("Valor de venda: ").append(dollarInformation.getSell()).append("\n")
                .append(getVariationEmoji()).append(" ").append("Variação: ").append(dollarInformation.getVariation()).append("\n").toString();
    }

    private NarutoCharacter getRandomNarutoCharacter() {
        int randomId = getRandomNumber(narutoCharacterService.count() + 1);
        return narutoCharacterService.findById((long) randomId);
    }

    private String getRandomEmoji() {
        List<String> possibleEmojis = List.of("crossed_swords", "knife", "dagger", "shield");
        String emojiAlias = possibleEmojis.get(getRandomNumber(possibleEmojis.size()));
        return EmojiManager.getForAlias(emojiAlias).getUnicode();
    }

    private String getDollarEmoji() {
        return EmojiManager.getForAlias("dollar").getUnicode();
    }

    private String getDollarFlyingEmoji() {
        return EmojiManager.getForAlias("money_with_wings").getUnicode();
    }

    private String getVariationEmoji() {
        return EmojiManager.getForAlias("twisted_rightwards_arrows").getUnicode();
    }

    @SneakyThrows
    private DollarDTO getDollarInformation() {
        String url = "https://api.hgbrasil.com/finance";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        if (response.getStatus() != 200)
            throw new Exception("Error getting dollar value");

        JSONObject dollarJson = response.getBody().getObject()
                .getJSONObject("results")
                .getJSONObject("currencies")
                .getJSONObject("USD");

        return new DollarDTO(
                dollarJson.getString("buy").substring(0, 4),
                dollarJson.getString("sell").substring(0, 4),
                dollarJson.getString("variation").substring(0,
                        dollarJson.getDouble("variation") < 0 ? 5 : 4)
        );


    }

}
