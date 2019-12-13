package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.CatPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CatPhotosJob {
    @Autowired private CatPhotosService catPhotosService;

    // Every 4 hours
    @Scheduled(cron = "0 0 */4 * * *", zone = "GMT-3")
    public void run() {
        catPhotosService.tweetCatPhoto();
    }

}
