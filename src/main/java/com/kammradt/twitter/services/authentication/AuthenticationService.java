package com.kammradt.twitter.services.authentication;

import com.kammradt.twitter.enums.BotNameEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class AuthenticationService {

    @Autowired private Environment env;


    public Twitter authenticate(BotNameEnum name) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        
        cb.setDebugEnabled(false)
                .setOAuthConsumerKey(env.getProperty("consumerKey." + name))
                .setOAuthConsumerSecret(env.getProperty("consumerSecret." + name))
                .setOAuthAccessToken(env.getProperty("accessToken." + name))
                .setOAuthAccessTokenSecret(env.getProperty("accessTokenSecret." + name));

        TwitterFactory tf = new TwitterFactory(cb.build());

        return tf.getInstance();
    }

}