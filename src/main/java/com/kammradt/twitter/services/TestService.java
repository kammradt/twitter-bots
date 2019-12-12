package com.kammradt.twitter.services;

import com.kammradt.twitter.enums.BotNameEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TestService {

    @Autowired
    private AuthenticationService authenticationService;

    public void test() {
        Twitter twitter = authenticationService.authenticate(BotNameEnum.BOTFTSDECACHORRO);

        try {
            System.out.println(twitter.getScreenName());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
