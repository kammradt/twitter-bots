package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.DogPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DogPhotosJob {

    @Autowired private DogPhotosService dogPhotosService;

    // Every 4 hours
    @Scheduled(cron = "0 0 */4 * * *", zone = "GMT-3")
    public void run() {
        dogPhotosService.tweetDogPhoto();
    }

}
