package com.kammradt.twitter.services;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.kammradt.twitter.domain.girls.MeanGirl;
import com.kammradt.twitter.domain.naruto.NarutoCharacter;
import com.kammradt.twitter.enums.BotNameEnum;
import com.kammradt.twitter.services.authentication.AuthenticationService;
import com.kammradt.twitter.services.databaseAccess.MeanGirlService;
import com.vdurmont.emoji.EmojiManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.kammradt.twitter.utils.FileUtils.getInputStreamFromUrl;
import static com.kammradt.twitter.utils.RandomUtils.getRandomEmoji;

@Service
public class MeanGirlsWednesdayService {

    @Autowired private AuthenticationService authenticationService;
    @Autowired private MeanGirlService meanGirlService;


    @SneakyThrows
    public void tweetMeanGirlsWithImage() {
        Twitter twitter = authenticationService.authenticate(BotNameEnum.DIADEUSARROSA);

        String text = isWendsday() ? getWendsdayText() : getRegularText();
        MeanGirl meanGirl = meanGirlService.getRandomMeanGirl();

        StatusUpdate tweetThatWillBeDone = new StatusUpdate(text);
        tweetThatWillBeDone.setMedia("", getInputStreamFromUrl(meanGirl.getImageUrl()));

        twitter.updateStatus(tweetThatWillBeDone);
    }

    private String getRegularText() {
        StringBuilder sadEmojis = getSadEmojis();
        String regular = sadEmojis.toString();
        String reversed = sadEmojis.reverse().toString();
        return (regular + "Não é quarta-feira" + reversed + "\n").repeat(7);
    }

    private StringBuilder getSadEmojis() {
        List<String> SAD_EMOJIS = List.of(
                "pensive", "cold_sweat", "dizzy_face",
                "worried", "anguished", "droplet",
                "injured", "pleading_face", "sweat",
                "persevere", "unamused", "sleepy",
                "tired_face", "scream", "confounded",
                "disappointed", "cry", "sob", "disappointed_relieved",
                "weary", "fearful", "sleeping", "sick",
                "crying_cat_face", "sweat_drops", "nauseated_face",
                "thumbsdown", "slightly_frowning", "frowning_face"
        );

        StringBuilder sadEmojisText = new StringBuilder();
        for (int i = 0; i < 5; i++)
            sadEmojisText.append(getRandomEmoji(SAD_EMOJIS));

        return sadEmojisText;
    }

    private String getWendsdayText() {
        String rainbowLine = getRainbowLine();
        String firstLine = getFirstLine();
        String secondLine = getSecondLine();

        return (rainbowLine + firstLine + secondLine).repeat(2) +
                rainbowLine;
    }

    private String getFirstLine() {
        // CHEGOU O DIA
        String firstLineText = "\uD835\uDE3E\uD835\uDE43\uD835\uDE40\uD835\uDE42\uD835\uDE4A\uD835\uDE50 \uD835\uDE4A \uD835\uDE3F\uD835\uDE44\uD835\uDE3C";
        StringBuilder firstLine = getFirstLineOfEmojis();

        return new StringBuilder()
                .append(firstLine)
                .append(firstLineText)
                .append(firstLine.reverse())
                .append("\n")
                .toString();
    }

    private StringBuilder getFirstLineOfEmojis() {
        return new StringBuilder()
                .append(EmojiManager.getForAlias("rose").getUnicode())
                .append(EmojiManager.getForAlias("cherry_blossom").getUnicode())
                .append(EmojiManager.getForAlias("tulip").getUnicode())
                .append(EmojiManager.getForAlias("star").getUnicode())
                .append(EmojiManager.getForAlias("sparkles").getUnicode());
    }

    private String getSecondLine() {
        // DE USAR ROSA
        String secondLineText = "\uD835\uDE3F\uD835\uDE40 \uD835\uDE50\uD835\uDE4E\uD835\uDE3C\uD835\uDE4D \uD835\uDE4D\uD835\uDE4A\uD835\uDE4E\uD835\uDE3C";
        StringBuilder secondLine = getSecondLineOfEmojis();
        return new StringBuilder()
                .append(secondLine)
                .append(secondLineText)
                .append(secondLine.reverse())
                .append("\n")
                .toString();
    }

    private StringBuilder getSecondLineOfEmojis() {
        return new StringBuilder()
                .append(EmojiManager.getForAlias("cherry_blossom").getUnicode().repeat(3))
                .append(EmojiManager.getForAlias("star").getUnicode())
                .append(EmojiManager.getForAlias("sparkles").getUnicode());
    }

    private String getRainbowLine() {
        String rainbow = EmojiManager.getForAlias("rainbow").getUnicode();
        String space = "ㅤ";
        return (rainbow + space).repeat(8) + rainbow + "\n";
    }

    public boolean isWendsday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
    }
}
