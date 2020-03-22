package com.kammradt.twitter.bots.dog;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DogPhotosJob {

    private final DogPhotosService dogPhotosService;

    // Every 3 hours
    @Scheduled(cron = "0 0 */3 * * *")
    public void run() {
        dogPhotosService.tweetDogPhoto();
    }

}
