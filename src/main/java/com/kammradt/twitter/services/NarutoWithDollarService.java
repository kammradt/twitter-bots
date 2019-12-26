package com.kammradt.twitter.services;

import com.kammradt.twitter.domain.NarutoCharacter;
import com.kammradt.twitter.enums.BotNameEnum;
import com.kammradt.twitter.services.authentication.AuthenticationService;
import com.kammradt.twitter.services.databaseAccess.NarutoCharacterService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

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
        StatusUpdate tweetThatWillBeDone = new StatusUpdate(randomNarutoCharacter.getName());
        tweetThatWillBeDone.setMedia("", getInputStreamFromUrl(randomNarutoCharacter.getImageUrl()));

        twitter.updateStatus(tweetThatWillBeDone);
    }

    private NarutoCharacter getRandomNarutoCharacter() {
        int randomId = getRandomNumber(narutoCharacterService.count() + 1);
        return narutoCharacterService.findById((long) randomId);
    }

}
