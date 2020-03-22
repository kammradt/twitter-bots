package com.kammradt.twitter.bots.naruto;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NarutoWithDollarJob {

    private final NarutoWithDollarService narutoWithDollarService;

    // Every day at 8:00 A.M. and 8:00 P.M.
    @Scheduled(cron = "0 0 11,23 * * *")
    void run() {
        narutoWithDollarService.tweetNarutoImageWithDollar();
    }

}
