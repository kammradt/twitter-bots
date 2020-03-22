package com.kammradt.twitter.bots.meangirls;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MeanGirlsWednesdayJob {

    private final MeanGirlsWednesdayService meanGirlsWednesdayService;

    // Every day at 8:00 A.M.
    @Scheduled(cron = "0 0 11 * * *")
    void run() {
        // meanGirlsWednesdayService.tweetMeanGirlsWithImage();
    }

}
