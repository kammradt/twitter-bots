package com.kammradt.twitter.bots.cat;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CatPhotosJob {

    private final CatPhotosService catPhotosService;

    // Every 3 hours
    @Scheduled(cron = "0 0 */3 * * *")
    public void run() {
        catPhotosService.tweetCatPhoto();
    }

}
