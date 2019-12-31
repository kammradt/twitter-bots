package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.MeanGirlsWednesdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MeanGirlsWednesdayJob {
    @Autowired
    private MeanGirlsWednesdayService meanGirlsWednesdayService;

    // Every day at 8:00 A.M.
    @Scheduled(cron = "0 0 11 * * *")
    void run() {
        meanGirlsWednesdayService.tweetMeanGirlsWithImage();
    }

}
