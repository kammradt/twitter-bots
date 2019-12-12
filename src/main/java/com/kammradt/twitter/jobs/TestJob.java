package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    @Autowired private TestService testService;

    // Every day at 8:00 A.M.
    // @Scheduled(cron = "0 8 * * *", zone = "GMT-3")

    // Every day at 8:00 A.M. and 8:00 P.M.
    // @Scheduled(cron = "0 8,20 * * *", zone = "GMT-3")

    @Scheduled(cron = "*/30 * * * * *", zone = "GMT-3")
    public void run() {
        testService.test();
    }



}
