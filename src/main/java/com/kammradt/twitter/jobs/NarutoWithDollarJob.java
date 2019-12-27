package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.NarutoWithDollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NarutoWithDollarJob {

    @Autowired private NarutoWithDollarService narutoWithDollarService;

    // Every day at 8:00 A.M. and 8:00 P.M.
    @Scheduled(cron = "0 0 11,23 * * *")
    void run() {
        narutoWithDollarService.tweetNarutoImageWithDollar();
    }
}
