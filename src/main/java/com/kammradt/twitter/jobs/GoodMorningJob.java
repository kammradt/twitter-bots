package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.GoodMorningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoodMorningJob {

    @Autowired private GoodMorningService goodMorningService;

    // Every day at 8:00 A.M.
    // @Scheduled(cron = "0 8 * * *", zone = "GMT-3")

    // Every day at 8:00 A.M. and 8:00 P.M.
    // @Scheduled(cron = "0 8,20 * * *", zone = "GMT-3")


    @Scheduled(cron = "0 8 * * *", zone = "GMT-3")
    public void run() {
        goodMorningService.tweetGoodMorningWithEmoji();
    }
}