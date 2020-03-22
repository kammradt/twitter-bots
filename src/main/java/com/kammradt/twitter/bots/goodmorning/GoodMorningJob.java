package com.kammradt.twitter.bots.goodmorning;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GoodMorningJob {

    private final GoodMorningService goodMorningService;

    // Every day at 8:00 A.M.
    @Scheduled(cron = "0 0 11 * * *")
    public void run() {
        goodMorningService.tweetGoodMorningWithEmoji();
    }

}
