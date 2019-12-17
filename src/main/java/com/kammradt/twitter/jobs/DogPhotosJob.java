package com.kammradt.twitter.jobs;

import com.kammradt.twitter.services.DogPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DogPhotosJob {

    @Autowired private DogPhotosService dogPhotosService;

    // Every 3 hours
    @Scheduled(cron = "0 0 */3 * * *")
    public void run() {
        dogPhotosService.tweetDogPhoto();
    }

}
